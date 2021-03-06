/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine;

import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.GameResults;


/**
 *
 * @author Kamil-Tomasz
 */
public interface GameEngine {
    int getTotalNumberOfTurn();
    void debugMode(boolean debug);
    void reset();
    boolean hasNextTurn();
    GameEngine nextTurn() throws NumberOfTurnsHasExceededException;
    GameResults getGameResults();
}
