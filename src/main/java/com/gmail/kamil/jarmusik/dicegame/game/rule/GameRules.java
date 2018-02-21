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
public interface GameRules {
    int rollOfDices();
    int getNumberOfTurns();
    int getNumberOfDices();
    int getNumberOfRolls();
    int getNumberOfWallsOfDice();
    BigDecimal maxPointsToEndTurn(int numberOfRollCurrent);
    RulesOfWinning getRulesOfWinning();
    MasterGame getMasterGame();
}
