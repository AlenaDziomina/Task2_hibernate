/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.dao;

import com.epam.testapp.connection.IConnectionPool;
import com.epam.testapp.dao.jdbc.IGenericQuery;
import com.epam.testapp.dao.jdbc.RowMapper;
import com.epam.testapp.exception.DaoConnectException;
import com.epam.testapp.exception.DaoException;
import com.epam.testapp.presentation.form.News;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alena_Grouk
 */
public class JdbcNewsDao implements INewsDao{
    private static final String DB_NEWS_ID = "NEWS_ID";
    private static final String DB_NEWS_TITLE = "TITLE";
    private static final String DB_NEWS_DATE = "NEWS_DATE";
    private static final String DB_NEWS_BRIEF = "BRIEF";
    private static final String DB_NEWS_CONTENT = "CONTENT";
    private static final String SELECT_QUERY = "SELECT* from NEWS ORDER BY NEWS_DATE DESC";
    private static final String SELECT_BY_ID_QUERY = "SELECT* from NEWS where NEWS_ID = ?";
    private static final String UPDATE_QUERY = "UPDATE NEWS set TITLE= ?, NEWS_DATE = ?, BRIEF = ?, CONTENT = ? where NEWS_ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO NEWS (TITLE, NEWS_DATE, BRIEF, CONTENT) VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUERY = "Delete from NEWS where NEWS_ID = ?";
    private IConnectionPool connectionPool;
    private IGenericQuery genericQuery;
    private final RowMapper rowMapper = (RowMapper) (ResultSet rs) -> {
        News bean = new News();
        bean.setId(rs.getInt(DB_NEWS_ID));
        bean.setTitle(rs.getString(DB_NEWS_TITLE));
        bean.setDate(rs.getDate(DB_NEWS_DATE));
        bean.setBrief(rs.getString(DB_NEWS_BRIEF));
        bean.setContent(rs.getString(DB_NEWS_CONTENT));
        return bean;
    };

    @Override
    public List getList() {
        List<News> newsList = null;
        Connection connection = null;
        try {
            connection = getConnectionPool().getConnection();
            newsList = getGenericQuery().loadQuery(SELECT_QUERY, new Object[0], connection, rowMapper);
        } catch (DaoException | DaoConnectException ex) {
            //Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    //Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return newsList;
    }
    
    @Override
    public News fetchById(Integer id) {
        Connection connection = null;
        List<News> newsList = null;
        try {
            connection = getConnectionPool().getConnection();
            Object[] params = new Object[]{id};
            newsList = getGenericQuery().loadQuery(SELECT_BY_ID_QUERY, params, connection, rowMapper);
        } catch (DaoConnectException | DaoException ex) {
            Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    //Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return newsList.get(0);
    }

    @Override
    public void save(News news) {
        Connection connection = null;
        try {
            connection = getConnectionPool().getConnection();
            if (news.getId() != null && news.getId() > 0) {
                Object[] params = new Object[]{news.getTitle(), news.getDate(),
                    news.getBrief(), news.getContent(), news.getId()};
                getGenericQuery().updateQuery(UPDATE_QUERY, params, connection);
            } else {
                Object[] params = new Object[]{news.getTitle(), news.getDate(),
                    news.getBrief(), news.getContent()};
                getGenericQuery().saveQuery(INSERT_QUERY, new Object[][]{params}, connection);
            }
            
        } catch (DaoConnectException | DaoException ex) {
            Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    //Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void remove(List<Integer> idList) {
        Connection connection = null;
        try {
            connection = getConnectionPool().getConnection();
            Object[] params = idList.toArray();
            getGenericQuery().deleteQuery(DELETE_QUERY, params, connection);
            
        } catch (DaoConnectException | DaoException ex) {
            Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    //Logger.getLogger(JdbcNewsDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @return the connectionPool
     */
    public IConnectionPool getConnectionPool() {
        return connectionPool;
    }

    /**
     * @param connectionPool the connectionPool to set
     */
    public void setConnectionPool(IConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * @return the genericQuery
     */
    public IGenericQuery getGenericQuery() {
        return genericQuery;
    }

    /**
     * @param genericQuery the genericQuery to set
     */
    public void setGenericQuery(IGenericQuery genericQuery) {
        this.genericQuery = genericQuery;
    }
}

    

