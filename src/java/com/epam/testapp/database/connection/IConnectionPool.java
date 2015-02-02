package com.epam.testapp.database.connection;

import com.epam.testapp.database.exception.DaoConnectException;
import java.sql.Connection;

public interface IConnectionPool {
    public Connection getConnection() throws DaoConnectException;
    public void returnConnection(Connection wrapperConnection) throws DaoConnectException;
}
