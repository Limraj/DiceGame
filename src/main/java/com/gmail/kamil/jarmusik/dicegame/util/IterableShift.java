/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.kamil.jarmusik.dicegame.util;

/**
 *
 * @author Kamil-Tomasz
 * @param <T>
 */
public interface IterableShift<T> {
    T next();
    int size();
    void reset();
}
