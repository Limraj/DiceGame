/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game;

import com.gmail.kamil.jarmusik.dicegame.game.engine.result.GameResults;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGameDeveloperMode implements Game, DeveloperMode {
    
    Game game;

    public DiceGameDeveloperMode(Game game) {
        this.game = game;
    }

    @Override
    public void start() {
        game.start();
    }

    @Override
    public GameResults getGameResults() {
        return game.getGameResults();
    }

    @Override
    public void debug(boolean debug) {
        ((DeveloperMode)game).debug(debug);
    }
}
