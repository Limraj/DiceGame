/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame;

import com.gmail.kamil.jarmusik.dicegame.game.*;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.PlayerResult;
import com.gmail.kamil.jarmusik.dicegame.game.player.Player;
import com.gmail.kamil.jarmusik.dicegame.game.rule.*;
import com.gmail.kamil.jarmusik.dicegame.game.rule.dice.DiceCube;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Kamil-Tomasz
 */
public class Start {
    
    public static void main(String[] args) {
        
        //Zad3
        System.out.println("Dice Game default:");
        Game game = GameFactory.newDiceGameDefault();
        game.debugMode(true);
        game.start();
        game.getGameResults().print();
        //wywołanie metody start resetuje wyniki i rozpoczyna nową grę;
        game.start();
        game.getGameResults().print();
        //można zdefiniować dowolną grę;
        System.out.println("-----------------");
        System.out.println("Dice game custom:");
        
        GameRules customRules = new DiceGameRules.Builder(new MasterGame() {
            
                @Override
                public boolean isWonTurn(int numberOfRollCurrent, int pointsRoll) {
                    return pointsRoll == 11;
                }

                @Override
                public boolean isLostTurn(int numberOfRollCurrent, int pointsRoll) {
                    return (pointsRoll == 5 || pointsRoll == 7 || pointsRoll == 13);
                }

                @Override
                public BigDecimal pointsScoredPerRoll(int numberOfRollCurrent, int pointsRoll) {
                    return BigDecimal.valueOf(pointsRoll)
                            .divide(BigDecimal.valueOf(numberOfRollCurrent), 2, RoundingMode.HALF_UP);
                }
            })
            .setNumberOfRolls(77)
            .setNumberOfTurns(14)
            .addDice(new DiceCube())
            .addDice(new DiceCube())
            .addDice(new DiceCube())
            .setRulesOfWinning(() -> (PlayerResult o1, PlayerResult o2) -> {
                int output = o2.getNumberOfWinningTurns() - o1.getNumberOfWinningTurns();
                return output == 0 ? o1.getPoints().compareTo(o2.getPoints()) : output;
            })
            .build();
        
        
        Game customGame = new DiceGame.Builder()
                .setRules(customRules)
                .addPlayer("Pierwszy")
                .addPlayer(new Player("Drugi"))
                .addPlayer("Trzeci")
                .addPlayer("Czwarty")
                .addPlayer("Piąty")
                .build();
        customGame.debugMode(true);
        customGame.start();
        customGame.getGameResults().print();
    }
}
