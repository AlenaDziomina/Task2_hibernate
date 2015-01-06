/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.dao.jdbc;

import com.epam.testapp.database.exception.DaoSqlException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Alena_Grouk
 */
public class GenericQuery implements IGenericQuery {
    
    private static final Logger LOGGER = Logger.getLogger(GenericQuery.class);
    private static final String PARAMS_IS_NULL_ERROR = "Query params should not be null";
    private static final String CLOSE_ERROR = "Error in close ResultSet or PreparedStatement.";
    private static final String SQL_ERROR = "SQL query is not done";

    @Override
    public <T> List<T> loadQuery(String query, Object[] params, Connection conn,
            RowMapper<T> mapper) throws DaoSqlException {
        
        List<T> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            int i = 0;
            while(rs.next()) {
                result.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            LOGGER.error(SQL_ERROR);
            throw new DaoSqlException(ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(CLOSE_ERROR);
            }
        }
        return result;
    }
    
    @Override
    public <T> List<Integer> deleteQuery(String query, Object[] params, 
            Connection conn) throws DaoSqlException {
        if (params == null) {
            throw new DaoSqlException(PARAMS_IS_NULL_ERROR);
        }
        List<Integer> resultList = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            for (Object param : params) {
                ps.setObject(1, param);
                ps.executeUpdate();
                ps.clearParameters();
            }
            return resultList;
        }
        catch (SQLException ex) {
            LOGGER.error(SQL_ERROR);
            throw new DaoSqlException(ex.getMessage(), ex);
        } finally {
            try {
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(CLOSE_ERROR);
            }
        }
    }


    

    @Override
    public <T> List<Integer> saveQuery(String query, String generatedName, Object[][] params, 
            Connection conn) throws DaoSqlException {
        
        if (params == null) {
            throw new DaoSqlException(PARAMS_IS_NULL_ERROR);
        }
        List<Integer> resultList = new ArrayList<>();
        String generatedColumns[] = {generatedName};
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query, generatedColumns);
            for (Object[] paramarray : params) {
                for (int i = 0; i < paramarray.length; i++) {
                    ps.setObject(i + 1, paramarray[i]);
                }
                if(ps.executeUpdate()>0){
                    rs = ps.getGeneratedKeys();
                    while (rs.next()){
                        resultList.add(rs.getInt(1));
                    }
                }
                ps.clearParameters();
            }
            return resultList;
        }
        catch (SQLException ex) {
            LOGGER.error(SQL_ERROR);
            throw new DaoSqlException(ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }

            } catch (SQLException ex) {
                LOGGER.error(CLOSE_ERROR);
            }
        }
    }

    @Override
    public <T> List<Integer> updateQuery(String query, Object[] params, 
            Connection conn) throws DaoSqlException {
        if (params == null) {
            throw new DaoSqlException(PARAMS_IS_NULL_ERROR);
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> resultList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            if(ps.executeUpdate()>0){
                rs = ps.getGeneratedKeys();
                while (rs.next()){
                    resultList.add(rs.getInt(1));
                }
            }
            return resultList;
        } catch (SQLException ex) {
            LOGGER.error(SQL_ERROR);
            throw new DaoSqlException(ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }

            } catch (SQLException ex) {
                LOGGER.error(CLOSE_ERROR);
            }
        }
    }
    
}
