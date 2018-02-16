/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.game.rule;

import com.gmail.kamil.jarmusik.dicegame.game.engine.result.PlayerResult;
import java.util.Comparator;

/**
 *
 * @author Kamil-Tomasz
 */
public interface RulesOfWinning {
    Comparator<PlayerResult> getRules();
}
