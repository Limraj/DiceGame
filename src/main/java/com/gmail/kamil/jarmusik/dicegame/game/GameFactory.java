/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game;

import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.rule.*;
import java.util.Set;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameFactory {
    
    public static Game newDiceGameDefault() {
        return new DiceGame.Builder()
                .addPlayer("Pierwszy")
                .addPlayer("Drugi")
                .setRules(DiceGameRules.newRules())
                .build();
    }
    
    public static Game newDiceGame(Set<PlayerGame> players, GameRules rules) {
        return new DiceGame.Builder()
                .addPlayers(players)
                .setRules(rules)
                .build();
    }
}
