/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.player;

import com.gmail.kamil.jarmusik.dicegame.util.ShiftRegister;
import java.util.Set;

/**
 *
 * @author Kamil-Tomasz
 */
public class PlayersShiftRegister extends ShiftRegister<PlayerGame> {

    public PlayersShiftRegister(Set<PlayerGame> players) {
        super(players);
    }
}
