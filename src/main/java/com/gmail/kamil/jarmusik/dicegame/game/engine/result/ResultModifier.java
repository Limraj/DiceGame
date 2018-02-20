/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.result;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Kamil-Tomasz
 */
class ResultModifier implements PlayerResultModifier {

    private final Set<Integer> winningTurns;
    private BigDecimal points;
    private int numberOfTurnCurrent;

    public ResultModifier() {
        winningTurns = new HashSet<>();
        numberOfTurnCurrent = 0;
        points = BigDecimal.ZERO;
    }

    @Override
    public void addPoints(BigDecimal points) {
        this.points = this.points.add(points);
    }
    
    @Override
    public void addTurn() {
        numberOfTurnCurrent++;
    }

    @Override
    public PlayerResult toPlayerResult() {
        return new Result(numberOfTurnCurrent, points, winningTurns.size());
    }

    @Override
    public void reset() {
        this.winningTurns.clear();
        this.numberOfTurnCurrent = 0;
        this.points = BigDecimal.ZERO;
    }

    @Override
    public void addWinningTurn(int turn) {
        winningTurns.add(turn);
    }
}
