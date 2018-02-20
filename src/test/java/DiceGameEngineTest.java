/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.gmail.kamil.jarmusik.dicegame.game.engine.DiceGameEngine;
import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.GameResults;
import com.gmail.kamil.jarmusik.dicegame.game.engine.result.PlayerResult;
import com.gmail.kamil.jarmusik.dicegame.game.player.Player;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.rule.DiceGameRules;
import com.gmail.kamil.jarmusik.dicegame.game.rule.GameRules;
import com.gmail.kamil.jarmusik.dicegame.game.rule.dice.DiceCubeOnlyTest;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGameEngineTest {

    private static DiceGameEngine engine;
    private static PlayerGame player1;
    private static PlayerGame player2;
    
    @BeforeAll
    public static void setup() {
        player1 = new Player("Pierwszy");
        player2 = new Player("Drugi");
        Set<PlayerGame> players = new LinkedHashSet<>();
        players.add(player1);
        players.add(player2);
        
        Integer[] sequenceForDice = {1,2,3,4,5,6,5,6,3,4,1,2,6,3,6,5,4,3,2,1};
        Integer[] sequenceForDice2 ={1,2,6,3,6,5,4,3,2,1,1,2,3,4,5,6,5,6,3,4};

        GameRules rules = new DiceGameRules.Builder()
                .setNumberOfTurns(5)
                .setNumberOfRolls(10)
                .addDice(new DiceCubeOnlyTest(sequenceForDice))
                .addDice(new DiceCubeOnlyTest(sequenceForDice2))
                .build();
        engine = new DiceGameEngine(players, rules);
        engine.debugMode(true);
    }

    @Test
    public void testNextTurn0() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        Assertions.assertEquals(BigDecimal.valueOf(0), result1.getPoints());
        Assertions.assertEquals(BigDecimal.valueOf(0), result2.getPoints());
        
        Assertions.assertEquals(0, result1.getCurrentTurnNumber());
        Assertions.assertEquals(0, result2.getCurrentTurnNumber());
        
        Assertions.assertEquals(0, result1.getNumberOfWinningTurns());
        Assertions.assertEquals(0, result2.getNumberOfWinningTurns());
    }
    
    @Test
    public void testNextTurn1() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        Assertions.assertEquals(BigDecimal.valueOf(35.14), result1.getPoints());
        Assertions.assertEquals(BigDecimal.valueOf(18.57), result2.getPoints());
        
        Assertions.assertEquals(1, result1.getCurrentTurnNumber());
        Assertions.assertEquals(1, result2.getCurrentTurnNumber());
        
        Assertions.assertEquals(0, result1.getNumberOfWinningTurns());
        Assertions.assertEquals(1, result2.getNumberOfWinningTurns());
        
        Assertions.assertEquals(player2, results.getLeader());
    }

    @Test
    public void testNextTurn2() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        Assertions.assertEquals(BigDecimal.valueOf(35.14), result1.getPoints());
        Assertions.assertEquals(BigDecimal.valueOf(53.71), result2.getPoints());
        
        Assertions.assertEquals(2, result1.getCurrentTurnNumber());
        Assertions.assertEquals(2, result2.getCurrentTurnNumber());
        
        Assertions.assertEquals(1, result1.getNumberOfWinningTurns());
        Assertions.assertEquals(1, result2.getNumberOfWinningTurns());
        
        Assertions.assertEquals(player1, results.getLeader());
    }
    
    @Test
    public void testNextTurn3() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        Assertions.assertEquals(BigDecimal.valueOf(53.71), result1.getPoints());
        Assertions.assertEquals(BigDecimal.valueOf(53.71), result2.getPoints());
        
        Assertions.assertEquals(3, result1.getCurrentTurnNumber());
        Assertions.assertEquals(3, result2.getCurrentTurnNumber());
        
        Assertions.assertEquals(2, result1.getNumberOfWinningTurns());
        Assertions.assertEquals(2, result2.getNumberOfWinningTurns());
        //oboje mają tyle samo punktów;
        //Assertions.assertEquals(player2, results.getLeader());
    }
    
    @Test
    public void testNextTurn4() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        Assertions.assertEquals(BigDecimal.valueOf(88.85), result1.getPoints());
        Assertions.assertEquals(BigDecimal.valueOf(72.28), result2.getPoints());
        
        Assertions.assertEquals(4, result1.getCurrentTurnNumber());
        Assertions.assertEquals(4, result2.getCurrentTurnNumber());
        
        Assertions.assertEquals(2, result1.getNumberOfWinningTurns());
        Assertions.assertEquals(3, result2.getNumberOfWinningTurns());
        
        Assertions.assertEquals(player2, results.getLeader());
    }
    
    @Test
    public void testNextTurn5() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        Assertions.assertEquals(BigDecimal.valueOf(88.85), result1.getPoints());
        Assertions.assertEquals(BigDecimal.valueOf(107.42), result2.getPoints());
        
        Assertions.assertEquals(5, result1.getCurrentTurnNumber());
        Assertions.assertEquals(5, result2.getCurrentTurnNumber());
        
        Assertions.assertEquals(3, result1.getNumberOfWinningTurns());
        Assertions.assertEquals(3, result2.getNumberOfWinningTurns());
        
        Assertions.assertEquals(player1, results.getLeader());
    }
}

