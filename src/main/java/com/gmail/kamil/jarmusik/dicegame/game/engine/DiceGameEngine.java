package com.gmail.kamil.jarmusik.dicegame.game.engine;

import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.kamil.jarmusik.dicegame.game.engine.log.TurnLoggable;
import com.gmail.kamil.jarmusik.dicegame.game.engine.log.TurnLogger;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.GameResults;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.GameResultsModifier;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.ResultsModifier;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayersShiftRegister;
import com.gmail.kamil.jarmusik.dicegame.game.rule.GameRules;
import com.gmail.kamil.jarmusik.dicegame.util.IterableShift;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGameEngine implements GameEngine {
    
    private int totalNumberOfTurns;
    private final GameResultsModifier gameResultsModifier;
    private final GameRules rules;
    private final IterableShift<PlayerGame> playersRegisterShift;
    private final TurnLoggable logger;
    private boolean debugMode;

    public DiceGameEngine(Set<PlayerGame> players, GameRules rules) {
        System.out.print("Load engine...");
        this.gameResultsModifier = new ResultsModifier(players, rules.getRulesOfWinning());
        this.rules = rules;
        this.totalNumberOfTurns = 0;
        this.playersRegisterShift = new PlayersShiftRegister(players);
        this.logger = new TurnLogger();
        this.debugMode = false;
        System.out.println(" ok");
    }

    @Override
    public GameEngine nextTurn() throws NumberOfTurnsHasExceededException {
        checkHasNextTurnAndIterate();
        try {
            executeFor(playersRegisterShift.next());
        } catch (PlayerHasNotBeenAddedToGameException ex) {
            Logger.getLogger(DiceGameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
    private void checkHasNextTurnAndIterate() throws NumberOfTurnsHasExceededException {
        if(!hasNextTurn())
            throw new NumberOfTurnsHasExceededException();
        totalNumberOfTurns++;
    }
    
    private void executeFor(PlayerGame player) throws PlayerHasNotBeenAddedToGameException {
        gameResultsModifier.addTurnFor(player);
        logger.startTurnLog(player, gameResultsModifier.toGameResults().getPlayerResultFor(player));
        for (int numberOfRollCurrent = 1; numberOfRollCurrent < rules.getNumberOfRolls() + 1; numberOfRollCurrent++) {
            int pointsRoll = rules.rollOfDices();
            if(debugMode)
                logger.turnLog(numberOfRollCurrent, pointsRoll, rules.getMasterGame());
            if(isLostThenAddPoints(numberOfRollCurrent, player, pointsRoll))
                break;
            if(isWonThenAddWinningTurnOrNotThenAddPoints(numberOfRollCurrent, player, pointsRoll))
                break;
        }
        GameResults results = gameResultsModifier.toGameResults();
        logger.endTurnLog(results.getPlayerResultFor(player), results.getLeader());
    }

    private boolean isLostThenAddPoints(int numberOfRollCurrent, PlayerGame player, int pointsRoll) throws PlayerHasNotBeenAddedToGameException {
        if(rules.getMasterGame().isLostTurn(numberOfRollCurrent, pointsRoll)) {
            gameResultsModifier.addPointsFor(player, rules.maxPointsToEndTurn(numberOfRollCurrent));
            return true;
        }
        return false;
    }
    
    private boolean isWonThenAddWinningTurnOrNotThenAddPoints(int numberOfRollCurrent, PlayerGame player, int pointsRoll) throws PlayerHasNotBeenAddedToGameException {
        if(rules.getMasterGame().isWonTurn(numberOfRollCurrent, pointsRoll)) {
            gameResultsModifier.addWinningTurnFor(player);
            return true;
        }
        gameResultsModifier.addPointsFor(player, rules.getMasterGame().pointsScoredPerRoll(numberOfRollCurrent, pointsRoll));
        return false;
    }

    @Override
    public void reset() {
        totalNumberOfTurns = 0;
        playersRegisterShift.reset();
        gameResultsModifier.reset();
    }

    @Override
    public int getTotalNumberOfTurn() {
        return totalNumberOfTurns;
    }

    @Override
    public boolean hasNextTurn() {
        return totalNumberOfTurns < rules.getNumberOfTurns() * playersRegisterShift.size();
    }

    @Override
    public GameResults getGameResults() {
        return gameResultsModifier.toGameResults();
    }

    @Override
    public void debugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
