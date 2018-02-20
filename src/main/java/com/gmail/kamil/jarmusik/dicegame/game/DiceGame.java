/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game;

import com.gmail.kamil.jarmusik.dicegame.game.engine.DiceGameEngine;
import com.gmail.kamil.jarmusik.dicegame.game.engine.GameEngine;
import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.GameResults;
import com.gmail.kamil.jarmusik.dicegame.game.player.Player;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.rule.DiceGameRules;
import com.gmail.kamil.jarmusik.dicegame.game.rule.GameRules;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGame implements Game, DeveloperMode {
    
    GameEngine engine;
    
    public static Game newGame() {
        return new Builder().addPlayer("Pierwszy").addPlayer("Drugi").build();
    }
    
    private DiceGame(Set<PlayerGame> players, GameRules rules) {
        System.out.println("Load game...");
        engine = new DiceGameEngine(players, rules);
    }

    @Override
    public void debug(boolean debug) {
        engine.debugMode(debug);
    }

    public static class Builder {

        //Set - nie chce żeby gracze mogli się powtarzać;
        private final Set<PlayerGame> players;
        private GameRules rules;
        
        public Builder() {
            //LinkedHashSet - chcę zachować kolejność dodawanych graczy;
            players = new LinkedHashSet<>();
            rules = DiceGameRules.newRules();
        }

        public Builder addPlayer(PlayerGame player) {
            players.add(player);
            return this;
        }

        public Builder addPlayer(String namePlayer) {
            PlayerGame player = new Player(namePlayer);
            players.add(player);
            return this;
        }

        public Builder addPlayers(LinkedHashSet<PlayerGame> players) {
            this.players.addAll(players);
            return this;
        }

        public Builder setRules(GameRules rules) {
            this.rules = rules;
            return this;
        }

        public Game build() {
            if(players.isEmpty())
                throw new IllegalStateException("No player has been added.");
            return new DiceGame(players, rules);
        }
    }

    @Override
    public void start() {
        System.out.println("Start game!");
        try {
            execute();
        } catch (NumberOfTurnsHasExceededException ex) {
            Logger.getLogger(DiceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void execute() throws NumberOfTurnsHasExceededException {
        engine.reset();
        while(engine.hasNextTurn())
            engine.nextTurn();
    }
    
    @Override
    public GameResults getGameResults() {
        return engine.getGameResults();
    }
}
