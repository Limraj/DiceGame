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
public interface Game {
    void start();
    GameResults getGameResults();
    void debugMode(boolean debug);
    @Deprecated
    void printResults();
}
