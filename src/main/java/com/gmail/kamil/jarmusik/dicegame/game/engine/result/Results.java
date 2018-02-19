/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.result;

import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.kamil.jarmusik.dicegame.game.player.Player;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.rule.RulesOfWinning;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Kamil-Tomasz
 */
class Results implements GameResults {
    
    private final Map<PlayerGame, PlayerResultModifier> results = new LinkedHashMap<>();
    private final Map<String, PlayerResultModifier> resultsForNames = new LinkedHashMap<>();
    private final RulesOfWinning rulesOfWinning;

    protected Results(Set<PlayerGame> players, RulesOfWinning rulesOfWinning) {
        players.forEach((player) -> {
            PlayerResultModifier modifier = new ResultModifier();
            results.put(player, modifier);
            resultsForNames.put(player.getName(), modifier);
        });
        this.rulesOfWinning = rulesOfWinning;
    }

    @Override
    public PlayerResult getPlayerResultFor(PlayerGame player) throws PlayerHasNotBeenAddedToGameException {
        return getPlayerResultFor(player.getName());
    }

    @Override
    public PlayerResult getPlayerResultFor(String namePlayer) throws PlayerHasNotBeenAddedToGameException {
        PlayerGame player = new Player(namePlayer);
        throwIfPlayerHasNotBeenAddedToGame(player);
        return results.get(player).toPlayerResult();
    }

    @Override
    public PlayerGame getLeader() {
        return Verdict.determineLeader(results, rulesOfWinning);
    }
    
    @Override
    public List<PlayerGame> getPeleton() {
        return Verdict.determinePeleton(results, rulesOfWinning);
    }
    
    @Override
    public void print() {
        System.out.println("-----------------");
        getPeleton().forEach(a -> {
            System.out.println(a + " - " + results.get(a).toPlayerResult());
        });
        System.out.println("Winner: " + getLeader());
        System.out.println("-----------------\n");
    }

    protected Map<PlayerGame, PlayerResultModifier> getResults() {
        return results;
    }
    
    protected void throwIfPlayerHasNotBeenAddedToGame(PlayerGame player) throws PlayerHasNotBeenAddedToGameException {
        if(!results.containsKey(player))
            throw new PlayerHasNotBeenAddedToGameException(player);
    }
}
