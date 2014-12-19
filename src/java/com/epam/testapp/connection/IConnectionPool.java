/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.connection;

import com.epam.testapp.exception.DaoConnectException;
import java.sql.Connection;

/**
 *
 * @author Alena_Grouk
 */
public interface IConnectionPool {
    public Connection getConnection() throws DaoConnectException;
    public void returnConnection(Connection wrapperConnection) throws DaoConnectException;
}
