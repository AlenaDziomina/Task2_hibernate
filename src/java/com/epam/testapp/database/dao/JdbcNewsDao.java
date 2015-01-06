/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.dao;

import com.epam.testapp.database.connection.IConnectionPool;
import com.epam.testapp.database.dao.jdbc.IGenericQuery;
import com.epam.testapp.database.dao.jdbc.RowMapper;
import com.epam.testapp.database.exception.DaoConnectException;
import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.database.exception.DaoSqlException;
import com.epam.testapp.model.News;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.apache.log4j.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(JdbcNewsDao.class);
    private static final String ERROR_CONNECT_RETURN = "Return connection is not done.";
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
    public List getList() throws DaoException {
        List<News> newsList = null;
        Connection connection = null;
        try {
            connection = getConnectionPool().getConnection();
            newsList = getGenericQuery().loadQuery(SELECT_QUERY, new Object[0], 
                    connection, rowMapper);
        } catch (DaoSqlException | DaoConnectException ex) {
            throw new DaoException(ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    LOGGER.error(ERROR_CONNECT_RETURN, ex);
                }
            }
        }
        return newsList;
    }
    
    @Override
    public News fetchById(Integer id) throws DaoException {
        Connection connection = null;
        List<News> newsList = null;
        try {
            connection = getConnectionPool().getConnection();
            Object[] params = new Object[]{id};
            newsList = getGenericQuery().loadQuery(SELECT_BY_ID_QUERY, params, 
                    connection, rowMapper);
        } catch (DaoSqlException | DaoConnectException ex) {
            throw new DaoException(ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    LOGGER.error(ERROR_CONNECT_RETURN, ex);
                }
            }
        }
        return newsList.get(0);
    }

    @Override
    public void save(News news) throws DaoException {
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
                getGenericQuery().saveQuery(INSERT_QUERY, DB_NEWS_ID,
                        new Object[][]{params}, connection);
            }         
        } catch (DaoSqlException | DaoConnectException ex) {
            throw new DaoException(ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    LOGGER.error(ERROR_CONNECT_RETURN, ex);
                }
            }
        }
    }

    @Override
    public void remove(List<Integer> idList) throws DaoException {
        Connection connection = null;
        try {
            connection = getConnectionPool().getConnection();
            Object[] params = idList.toArray();
            getGenericQuery().deleteQuery(DELETE_QUERY, params, connection);       
        } catch (DaoSqlException | DaoConnectException ex) {
            throw new DaoException(ex);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (DaoConnectException ex) {
                    LOGGER.error(ERROR_CONNECT_RETURN, ex);
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

    

