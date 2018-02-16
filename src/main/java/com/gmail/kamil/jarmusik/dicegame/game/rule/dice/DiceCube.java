/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.rule.dice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceCube implements Dice {

    private final List<Integer> diceImpl;
    private final Random random;
    
    public DiceCube() {
        this.diceImpl = Arrays.asList(new Integer[]{1,2,3,4,5,6});
        this.random = new Random();
    }
    
    @Override
    public int getNumberOfWalls() {
        return diceImpl.size();
    }

    @Override
    public int rollOfDice() {
        return diceImpl.get(random.nextInt(getNumberOfWalls()));
    }

}
