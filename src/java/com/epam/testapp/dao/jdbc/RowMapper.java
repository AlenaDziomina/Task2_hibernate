/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Functional interface of processing of the query result in entity.
 * @param <T> type of entity
 * @author Alena_Grouk
 */
public interface RowMapper <T> {
    T mapRow(ResultSet rs) throws SQLException;
}