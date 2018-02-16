/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.result;

import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.rule.RulesOfWinning;
import com.gmail.kamil.jarmusik.dicegame.util.SortMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Kamil-Tomasz
 */
public class Verdict {
    
    static PlayerGame determineLeader(Map<PlayerGame, PlayerResultModifier> modifiers, RulesOfWinning rulesOfWinning) {
        return determinePeleton(modifiers, rulesOfWinning).get(0);
    }
    
    static List<PlayerGame> determinePeleton(Map<PlayerGame, PlayerResultModifier> modifiers, RulesOfWinning rulesOfWinning) {
        List<PlayerGame> sorted = SortMap.sortByValueToKeyList(toMapPlayerResult(modifiers), rulesOfWinning.getRules());
        //System.out.println("Sorted: " + sorted);
        return Collections.unmodifiableList(sorted);
    }
    
    private static Map<PlayerGame, PlayerResult> toMapPlayerResult(Map<PlayerGame, PlayerResultModifier> modifiers) {
        return modifiers.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
            return entry.getValue().toPlayerResult();
        }));
    } 
}
