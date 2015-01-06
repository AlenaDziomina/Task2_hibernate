/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.dao.jdbc;

import com.epam.testapp.database.exception.DaoSqlException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Alena_Grouk
 */
public interface IGenericQuery {
    <T> List<Integer> deleteQuery(String query, Object[] params,
            Connection conn) throws DaoSqlException;
    <T> List<T> loadQuery(String query, Object[] params, Connection conn,
            RowMapper<T> mapper) throws DaoSqlException;
    <T> List<Integer> saveQuery(String query, String generatedName, 
            Object[][] params, Connection conn) throws DaoSqlException;
    <T> List<Integer> updateQuery(String query, Object[] params, Connection conn)
            throws DaoSqlException;

}
