/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.connection;

import com.epam.testapp.database.exception.DaoConnectException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import org.apache.log4j.Logger;

/**
 *
 * @author Alena_Grouk
 */
public class JdbcConnectionPool implements IConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(JdbcConnectionPool.class);
    private static final String DRIVER_ERROR = "Driver is not found.";
    private static final String CONNECT_ERROR = "Connection is not created.";
    private static final String CONNECT_DESTROY_ERROR = "Error in closing and "
            + "destroying pool connections.";
    private static final String CONNECT_TAKE_ERROR = "Can't take connection.";
    private static final String CONNECT_INVALID = "Connection is null or invalid";
    private static final String CONNECT_RETURN_ERROR = "Can't return connection.";
    private static final String CORRECT_PROXY_ERROR = "Connection must be returned.";
    private String dbUserName;
    private String dbPassword;
    private String dbUrl;
    private String dbDriverName;
    private int dbMaxConnections;
    private int dbMaxIdleConnections;
    private int dbIsvalidTimeout;
    private static BlockingQueue<ProxyConnection> queue;
    
    private JdbcConnectionPool(){}
    
    public void init() throws DaoConnectException{
        loadDriver();
        queue = new ArrayBlockingQueue<>(getDbMaxConnections());
        for (int i = 0; i < getDbMaxConnections(); i++) {
            queue.offer(createNewConnection());
        }
    }
    
    private void loadDriver() throws DaoConnectException {
        try {
            Class.forName(getDbDriverName());
        } catch (ClassNotFoundException ex) {
            LOGGER.error(DRIVER_ERROR);
            throw new DaoConnectException(DRIVER_ERROR, ex);
        }
    }
    
    private ProxyConnection createNewConnection() throws DaoConnectException{
        ProxyConnection conn = null;
        try {
            Connection connection = (Connection) DriverManager.getConnection(getDbUrl(), getDbUserName(), getDbPassword());
            conn = new ProxyConnection(connection);
        } catch (SQLException ex) {
            LOGGER.error(CONNECT_ERROR);
            throw new DaoConnectException(CONNECT_ERROR, ex);
        }
        return conn;
    }
    
    public void destroy() {
        while (! queue.isEmpty()) {
            try {
                ProxyConnection conn = queue.take();
                if (conn != null && conn.isValid(getDbIsvalidTimeout())) {
                    conn.reallyClose();
                }
            } catch (InterruptedException | SQLException ex) {
                LOGGER.error(CONNECT_DESTROY_ERROR);
            }
        }
    }
    
    @Override
    public Connection getConnection() throws DaoConnectException {
        Connection conn = null;
        try {
            conn = queue.take();
            if (conn == null || !conn.isValid(dbIsvalidTimeout)) {
                LOGGER.error(CONNECT_INVALID);
                throw new DaoConnectException(CONNECT_INVALID);
            }
        } catch (InterruptedException | SQLException ex) {
            LOGGER.error(CONNECT_TAKE_ERROR);
            throw new DaoConnectException(CONNECT_TAKE_ERROR, ex);
        }
        
        return conn;
    }

    @Override
    public void returnConnection(Connection conn) throws DaoConnectException {
        try {
            if (conn == null || !conn.isValid(dbIsvalidTimeout)) {
                LOGGER.error(CONNECT_INVALID);
                throw new DaoConnectException(CONNECT_INVALID);
            }
            queue.offer((ProxyConnection) conn);
        } catch (SQLException ex) {
            LOGGER.error(CONNECT_RETURN_ERROR);
            throw new DaoConnectException(CONNECT_RETURN_ERROR, ex);
        }
    }
    
    /**
     * @return the dbUserName
     */
    public String getDbUserName() {
        return dbUserName;
    }

    /**
     * @param dbUserName the dbUserName to set
     */
    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    /**
     * @return the dbPassword
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param dbPassword the dbPassword to set
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * @return the dbUrl
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * @param dbUrl the dbUrl to set
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * @return the dbDriverName
     */
    public String getDbDriverName() {
        return dbDriverName;
    }

    /**
     * @param dbDriverName the dbDriverName to set
     */
    public void setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }

    /**
     * @return the dbMaxConnections
     */
    public int getDbMaxConnections() {
        return dbMaxConnections;
    }

    /**
     * @param dbMaxConnections the dbMaxConnections to set
     */
    public void setDbMaxConnections(int dbMaxConnections) {
        this.dbMaxConnections = dbMaxConnections;
    }

    /**
     * @return the dbMaxIdleConnections
     */
    public int getDbMaxIdleConnections() {
        return dbMaxIdleConnections;
    }

    /**
     * @param dbMaxIdleConnections the dbMaxIdleConnections to set
     */
    public void setDbMaxIdleConnections(int dbMaxIdleConnections) {
        this.dbMaxIdleConnections = dbMaxIdleConnections;
    }

    /**
     * @return the dbIsvalidTimeout
     */
    public int getDbIsvalidTimeout() {
        return dbIsvalidTimeout;
    }

    /**
     * @param dbIsvalidTimeout the dbIsvalidTimeout to set
     */
    public void setDbIsvalidTimeout(int dbIsvalidTimeout) {
        this.dbIsvalidTimeout = dbIsvalidTimeout;
    }
    
    private static class ProxyConnection implements Connection {

    private final Connection connection;

    ProxyConnection(Connection connection) { // только в пакете
        this.connection = connection;
    }

    private void reallyClose() throws SQLException{
        connection.close();
    }

    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        LOGGER.error(CORRECT_PROXY_ERROR);
        throw new SQLException(CORRECT_PROXY_ERROR);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(sql);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback();
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
    }

}