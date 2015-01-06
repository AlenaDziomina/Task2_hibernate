/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.exception;

/**
 *
 * @author Alena_Grouk
 */
public class DaoSqlException extends Exception {

    public DaoSqlException(){
    }

    public DaoSqlException(String message, Throwable exception) {
        super(message, exception);
    }

    public DaoSqlException(Throwable exception) {
        super(exception);
    }

    public DaoSqlException(String message) {
        super(message);
    }

}
