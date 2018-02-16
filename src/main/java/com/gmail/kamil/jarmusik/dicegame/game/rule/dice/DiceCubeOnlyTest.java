/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.rule.dice;

import com.gmail.kamil.jarmusik.dicegame.util.ShiftRegister;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceCubeOnlyTest extends ShiftRegister<Integer> implements Dice {

    private final List<Integer> diceImpl;

    public DiceCubeOnlyTest(Integer... sequence) {
        super(Arrays.asList(sequence));
        this.diceImpl = Arrays.asList(new Integer[]{1,2,3,4,5,6});
    }
    
    @Override
    public int getNumberOfWalls() {
        return diceImpl.size();
    }

    @Override
    public int rollOfDice() {
        return next();
    }
}
