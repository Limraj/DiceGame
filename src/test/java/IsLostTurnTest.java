/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.gmail.kamil.jarmusik.dicegame.game.rule.DiceGameRules;
import com.gmail.kamil.jarmusik.dicegame.game.rule.GameRules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


/**
 *
 * @author Kamil-Tomasz
 */

public class IsLostTurnTest {

    static GameRules rules = DiceGameRules.newRules();

    @ParameterizedTest(name = "{index} => isLostTurn({0},{1})={2}")
    @CsvFileSource(resources = "is-lost-turn-data-test.csv")
    void testIsLostTurn(int numberOfRollCurrent, int pointsRoll, boolean expected) {
        Assertions.assertEquals(expected, rules.getMasterGame().isLostTurn(numberOfRollCurrent, pointsRoll));
    }
}
