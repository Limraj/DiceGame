/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.rule;

import com.gmail.kamil.jarmusik.dicegame.game.engine.result.PlayerResult;
import com.gmail.kamil.jarmusik.dicegame.game.rule.dice.Dice;
import com.gmail.kamil.jarmusik.dicegame.game.rule.dice.DiceCube;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGameRules implements GameRules {
    
    private final List<Dice> dices;
    private final int numberOfTurns;
    private final int numberOfRolls;
    private final int numberOfWallsOfDice;
    private final MasterGame masterGame;
    private final RulesOfWinning rulesOfWinning;
    
    public static GameRules newRules() {
        
        return new DiceGameRules.Builder(new MasterGame() {
                    @Override
                    public boolean isWonTurn(int numberOfRollCurrent, int pointsRoll) {
                        return (numberOfRollCurrent == 1 && (pointsRoll == 7 || pointsRoll == 11)) || pointsRoll == 5;
                    }
                    
                    @Override
                    public boolean isLostTurn(int numberOfRollCurrent, int pointsRoll) {
                        return numberOfRollCurrent == 1 && (pointsRoll == 2 || pointsRoll == 12);
                    }

                    @Override
                    public BigDecimal pointsScoredPerRoll(int numberOfRollCurrent, int pointsRoll) {
                        return BigDecimal.valueOf(pointsRoll).divide(BigDecimal.valueOf(numberOfRollCurrent), 2, RoundingMode.HALF_UP);
                    }
                })
                .addDice(new DiceCube())
                .addDice(new DiceCube())
                .setRulesOfWinning(() -> (PlayerResult o1, PlayerResult o2) -> {
                    return o1.getPoints().compareTo(o2.getPoints());
                })
                .setNumberOfRolls(10)
                .setNumberOfTurns(5)
                .build();
    }

    private DiceGameRules(List<Dice> dices, int numberOfTurn, MasterGame masterGame, int numberOfRolling, RulesOfWinning rulesOfWinning) {
        this.dices = dices;
        this.numberOfTurns = numberOfTurn;
        this.numberOfRolls = numberOfRolling;
        this.masterGame = masterGame;
        this.numberOfWallsOfDice = dices.get(0).getNumberOfWalls();
        this.rulesOfWinning = rulesOfWinning;
    }

    @Override
    public MasterGame getMasterGame() {
        return masterGame;
    }

    @Override
    public BigDecimal maxPointsToEndTurn(int numberOfRollCurrent) {
        BigDecimal accumulator = BigDecimal.ZERO;
        for (int i = numberOfRollCurrent; i < getNumberOfRolls() + 1; i++)
            accumulator = accumulator.add(getMasterGame().pointsScoredPerRoll(i, getNumberOfDices() * getNumberOfWallsOfDice()));
        return accumulator;
    }
    
    @Override
    public int rollOfDices() {
        int points = 0;
        return dices.stream().map((dice) -> dice.rollOfDice()).reduce(points, Integer::sum);
    }

    @Override
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    @Override
    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    @Override
    public int getNumberOfDices() {
        return dices.size();
    }

    @Override
    public int getNumberOfWallsOfDice() {
        return numberOfWallsOfDice;
    }

    @Override
    public RulesOfWinning getRulesOfWinning() {
        return rulesOfWinning;
    }

    public static class Builder {
    
        private final List<Dice> dices;
        private int numberOfTurns;
        private int numberOfRolls;
        private final MasterGame masterGame;
        private RulesOfWinning rulesOfWinning;

        public Builder(MasterGame masterGame) {
            this.masterGame = masterGame;
            dices = new ArrayList<>();
            rulesOfWinning = () -> (PlayerResult o1, PlayerResult o2) -> {
                return o1.getPoints().compareTo(o2.getPoints());
            };
            numberOfRolls = 10;
            numberOfTurns = 5;
        }
        
        public Builder(GameRules rules) {
            this.masterGame = rules.getMasterGame();
            dices = new ArrayList<>();
            rulesOfWinning = rules.getRulesOfWinning();
            numberOfRolls = rules.getNumberOfRolls();
            numberOfTurns = rules.getNumberOfTurns();
        }

        public Builder setNumberOfTurns(int numberOfTurns) {
            this.numberOfTurns = numberOfTurns;
            return this;
        }

        public Builder setNumberOfRolls(int numberOfRolls) {
            this.numberOfRolls = numberOfRolls;
            return this;
        }

        public Builder setRulesOfWinning(RulesOfWinning rulesOfWinning) {
            this.rulesOfWinning = rulesOfWinning;
            return this;
        }

        public Builder addDice(Dice dice) {
            dices.add(dice);
            return this;
        }
        
        public GameRules build() {
            if(dices.isEmpty())
                throw new IllegalStateException("No dice added.");
            return new DiceGameRules(dices, numberOfTurns, masterGame, numberOfRolls, rulesOfWinning);
        }
    }
}
