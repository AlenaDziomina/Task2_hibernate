package com.epam.testapp.database.dao.jdbc;

import com.epam.testapp.database.exception.DaoSqlException;
import java.sql.Connection;
import java.util.List;

public interface IGenericQuery {
    void deleteQuery(String query, Object[] params,
            Connection conn) throws DaoSqlException;
    <T> List<T> loadQuery(String query, Object[] params, Connection conn,
            RowMapper<T> mapper) throws DaoSqlException;
    Integer saveQuery(String query, String generatedName, 
            Object[] params, Connection conn) throws DaoSqlException;
    void updateQuery(String query, Object[] params, 
            Connection conn) throws DaoSqlException;

}
