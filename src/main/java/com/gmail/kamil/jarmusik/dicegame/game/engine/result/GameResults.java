/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.result;


import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameResults {
    PlayerResult getPlayerResultFor(String namePlayer) throws PlayerHasNotBeenAddedToGameException;
    PlayerResult getPlayerResultFor(PlayerGame player) throws PlayerHasNotBeenAddedToGameException;
    PlayerGame getLeader();
    List<PlayerGame> getPeleton();
    void printResults();
}
