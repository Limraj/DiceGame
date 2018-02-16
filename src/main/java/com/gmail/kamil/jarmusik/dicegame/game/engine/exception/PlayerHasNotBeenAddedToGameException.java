/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.exception;

import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;

/**
 *
 * @author Kamil-Tomasz
 */
public class PlayerHasNotBeenAddedToGameException extends Exception {
    
    public PlayerHasNotBeenAddedToGameException(PlayerGame player) {
        super("The data entered does not match any player added to the game. The Player entered:" + player);
    }

}
