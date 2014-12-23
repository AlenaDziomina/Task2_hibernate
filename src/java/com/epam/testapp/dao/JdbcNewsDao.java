/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.dao;

import com.epam.testapp.connection.IConnectionPool;
import com.epam.testapp.connection.JdbcConnectionPool;
import com.epam.testapp.dao.INewsDao;
import com.epam.testapp.dao.jdbc.IGenericQuery;
import com.epam.testapp.dao.jdbc.RowMapper;
import com.epam.testapp.exception.DaoConnectException;
import com.epam.testapp.exception.DaoException;
import com.epam.testapp.presentation.form.News;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alena_Grouk
 */
public class JdbcNewsDao implements INewsDao{
    
    private static final String SELECT_QUERY = "SELECT* from NEWS ORDER BY time DESC";
    private static final String SELECT_BY_ID_QUERY = "SELECT* from NEWS where NEWSID = ?";
    
    private IConnectionPool connectionPool;
    private IGenericQuery genericQuery;
    private final String DB_NEWS_ID = "NEWSID";
    private static final String DB_NEWS_TITLE = "TITLE";
    private static final String DB_NEWS_DATE = "NEWSDATE";
    private static final String DB_NEWS_BRIEF = "BRIEF";
    private static final String DB_NEWS_CONTENT = "CONTENT";
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
    }

    @Override
    public void remove(List<Integer> idList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    
    
//    public static final String SELECT_ORDERS = "SELECT* from NEWS ORDER BY time DESC";
//	public static final String FETCH_NEWS_ID = "SELECT* from NEWS where ID = ?";
//	public static final String INSERT_NEWS = "INSERT INTO news (title, time, brief, content) VALUES (?, ?, ?, ?)";
//	public static final String DELETE_NEWS = "Delete from news where id = ?";
//	public static final String UPDATE_NEWS = "UPDATE news set title= ?,time = ?, brief = ?, content = ? where id = ?";
//
//	private static final Logger logger = Logger.getLogger(JDBCNewsDAO.class);
//
//	private IConnectionPool connectionPool;
//
//	public JDBCNewsDAO() {
//	}
//
//	@Override
//	public void save(News entity) throws DaoException {
//		WrapperConnection wrapperConnection = null;
//		PreparedStatement preparedStatement = null;
//		int id = entity.getId();
//		try {
//			wrapperConnection = connectionPool.getConnection();
//			if (id == 0) {
//				preparedStatement = wrapperConnection
//						.prepareStatement(INSERT_NEWS);
//			} else {
//				preparedStatement = wrapperConnection
//						.prepareStatement(UPDATE_NEWS);
//				preparedStatement.setInt(5, id);
//			}
//			Date date = entity.getDate();
//			java.sql.Date sqlDate = DateUtils.toSqlDate(date);
//			preparedStatement.setString(1, entity.getTitle());
//			preparedStatement.setDate(2, sqlDate);
//			preparedStatement.setString(3, entity.getBrief());
//			preparedStatement.setString(4, entity.getContent());
//			preparedStatement.executeUpdate();
//		} catch (SQLException | ConnectionPoolException exc) {
//			logger.error(exc);
//			throw new DaoException();
//		} finally {
//			closeData(preparedStatement, wrapperConnection);
//		}
//
//	}
//
//	@Override
//	public void remove(int[] id) throws DaoException {
//		WrapperConnection wrapperConnection = null;
//		PreparedStatement preparedStatement = null;
//		try {
//			wrapperConnection = connectionPool.getConnection();
//			preparedStatement = wrapperConnection.prepareStatement(DELETE_NEWS);
//			for (int currentId : id) {
//				preparedStatement.setInt(1, currentId);
//				preparedStatement.executeUpdate();
//			}
//		} catch (SQLException | ConnectionPoolException exc) {
//			logger.error(exc);
//			throw new DaoException();
//		} finally {
//			closeData(preparedStatement, wrapperConnection);
//		}
//	}
//
//	@Override
//	public News fetchById(int id) throws DaoException {
//
//		ResultSet resultSet = null;
//		News news = null;
//		WrapperConnection wrapperConnection = null;
//		PreparedStatement preparedStatement = null;
//		try {
//			wrapperConnection = connectionPool.getConnection();
//			preparedStatement = wrapperConnection
//					.prepareStatement(FETCH_NEWS_ID);
//			preparedStatement.setInt(1, id);
//			resultSet = preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				news = initNews(resultSet);
//			}
//
//		} catch (SQLException | ConnectionPoolException exc) {
//			logger.error(exc);
//			throw new DaoException();
//		} finally {
//			closeData(preparedStatement, wrapperConnection);
//		}
//		return news;
//	}
//
//	@Override
//	public List<News> getList() throws DaoException {
//		News currentNews = null;
//		List<News> newsList = new ArrayList<News>();
//		WrapperConnection wrapperConnection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		try {
//			wrapperConnection = connectionPool.getConnection();
//			statement = wrapperConnection.createStatement();
//			resultSet = statement.executeQuery(SELECT_ORDERS);
//			while (resultSet.next()) {
//				currentNews = initNews(resultSet);
//				newsList.add(currentNews);
//			}
//		} catch (SQLException | ConnectionPoolException exc) {
//			logger.error(exc);
//			throw new DaoException();
//		} finally {
//			closeData(statement, wrapperConnection);
//		}
//		return newsList;
//	}
//
//	private void closeData(Statement statement,
//			WrapperConnection wrapperConnection) throws DaoException {
//		if (statement != null) {
//			try {
//				statement.close();
//			} catch (SQLException exc) {
//				logger.error(exc.getMessage());
//			}
//		}
//		if (wrapperConnection != null) {
//			try {
//				connectionPool.returnConnection(wrapperConnection);
//			} catch (ConnectionPoolException exc) {
//				logger.error(exc.getMessage());
//				throw new DaoException();
//			}
//		}
//	}
//
//	private News initNews(ResultSet resultSet) throws SQLException {
//		News news = null;
//		int id = resultSet.getInt(1);
//		String title = resultSet.getString(2);
//		Date date = resultSet.getDate(3);
//		String brief = resultSet.getString(4);
//		String content = resultSet.getString(5);
//		news = new News();
//		news.setBrief(brief);
//		news.setDate(date);
//		news.setContent(content);
//		news.setTitle(title);
//		news.setId(id);
//		return news;
//	}
//
//	public IConnectionPool getConnectionPool() {
//		return connectionPool;
//	}
//
//	public void setConnectionPool(IConnectionPool connectionPool) {
//		this.connectionPool = connectionPool;
//	}

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

    

