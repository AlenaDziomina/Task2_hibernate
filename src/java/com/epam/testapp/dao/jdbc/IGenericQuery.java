/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.dao.jdbc;

import com.epam.testapp.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Alena_Grouk
 */
public interface IGenericQuery {
    <T> List<Integer> deleteQuery(String query, Object[] params,
            Connection conn) throws DaoException;
    <T> List<T> loadQuery(String query, Object[] params, Connection conn,
            RowMapper<T> mapper) throws DaoException;
    <T> List<Integer> saveQuery(String query, Object[][] params, Connection conn)
            throws DaoException;
    <T> List<Integer> updateQuery(String query, Object[] params, Connection conn)
            throws DaoException;

}
