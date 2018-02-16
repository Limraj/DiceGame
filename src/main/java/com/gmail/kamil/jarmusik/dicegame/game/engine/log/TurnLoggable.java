/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.engine.log;

import com.gmail.kamil.jarmusik.dicegame.game.engine.result.PlayerResult;
import com.gmail.kamil.jarmusik.dicegame.game.player.PlayerGame;
import com.gmail.kamil.jarmusik.dicegame.game.rule.MasterGame;

/**
 *
 * @author Kamil-Tomasz
 */
public interface TurnLoggable {
    void startTurnLog(PlayerGame player, PlayerResult result);
    void turnLog(int numberOfRollCurrent, int pointsRoll, MasterGame master);
    void endTurnLog(PlayerResult resultPlayerCurrent, PlayerGame playerLeader);
}
