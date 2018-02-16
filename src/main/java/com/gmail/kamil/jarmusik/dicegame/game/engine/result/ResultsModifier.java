/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.result;

import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.rule.RulesOfWinning;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author Kamil-Tomasz
 */
public final class ResultsModifier extends Results implements GameResultsModifier {

    public ResultsModifier(Set<PlayerGame> players, RulesOfWinning rulesOfWinning) {
        super(players, rulesOfWinning);
    }

    @Override
    public void addPointsFor(PlayerGame player, BigDecimal points) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        getResults().get(player).addPoints(points);
    }

    @Override
    public void addTurnFor(PlayerGame player) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        getResults().get(player).addTurn();
    }

    @Override
    public GameResults toGameResults() {
        return (GameResults) this;
    }
    
    @Override
    public void addWinningTurnFor(PlayerGame player) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        PlayerResultModifier mod = getResults().get(player);
        mod.addWinningTurn(mod.toPlayerResult().getCurrentTurnNumber());
    }

    @Override
    public void reset() {
        getResults().entrySet().forEach((entry) -> {
            entry.getValue().reset();
        });
    }
}
