package com.gmail.kamil.jarmusik.dicegame.game.engine.result;

import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil-Tomasz
 */
public interface PlayerResult {
    int getCurrentTurnNumber();
    int getNumberOfWinningTurns();
    boolean isEmpty();
    BigDecimal getPoints();
}
