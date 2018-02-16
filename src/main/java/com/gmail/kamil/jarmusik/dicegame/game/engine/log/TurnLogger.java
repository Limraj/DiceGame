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
public class TurnLogger implements TurnLoggable {

    @Override
    public void startTurnLog(PlayerGame player, PlayerResult result) {
        System.out.println("Turn number: " + result.getCurrentTurnNumber() + ", for: " + player + "\nResult: " + result);
    }
    
    @Override
    public void turnLog(int numberOfRollCurrent, int pointsRoll, MasterGame master) {
        System.out.println("roll[" + numberOfRollCurrent + "]: " + pointsRoll + ", points: " + master.pointsScoredPerRoll(numberOfRollCurrent, pointsRoll));
    }
    
    @Override
    public void endTurnLog(PlayerResult resultPlayerCurrent, PlayerGame playerLeader) {
        System.out.println("Result turn: " + resultPlayerCurrent);
        System.out.println("Leader: " + playerLeader + "\n");
    }
    
}
