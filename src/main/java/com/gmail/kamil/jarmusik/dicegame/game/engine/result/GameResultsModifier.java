/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.result;

import com.gmail.kamil.jarmusik.dicegame.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import java.math.BigDecimal;


/**
 *
 * @author Kamil-Tomasz
 */
public interface GameResultsModifier {
    void addPointsFor(PlayerGame player, BigDecimal points) throws PlayerHasNotBeenAddedToGameException;
    void addWinningTurnFor(PlayerGame player) throws PlayerHasNotBeenAddedToGameException;
    void addTurnFor(PlayerGame player) throws PlayerHasNotBeenAddedToGameException;
    void reset();
    GameResults toGameResults();
}
