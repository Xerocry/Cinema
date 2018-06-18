/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.repository.mappers;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Оксана
 */
public interface AbstractMapper<T> {

    T findById(final int id) throws SQLException;
    Map<Integer, T> findAll() throws SQLException;
    void update(T item) throws SQLException;
    void update() throws SQLException;
    void closeConnection() throws SQLException;
    void clear();
}

