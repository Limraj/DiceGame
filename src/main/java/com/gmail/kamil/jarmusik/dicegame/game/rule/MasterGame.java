/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.rule;

import java.math.BigDecimal;

/**
 *
 * @author Kamil-Tomasz
 */
public interface MasterGame {
    boolean isWonTurn(int numberOfRollCurrent, int pointsRoll);
    boolean isLostTurn(int numberOfRollCurrent, int pointsRoll);
    BigDecimal pointsScoredPerRoll(int numberOfRollCurrent, int pointsRoll);
}
