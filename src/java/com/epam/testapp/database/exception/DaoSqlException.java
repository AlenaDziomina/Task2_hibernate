package com.epam.testapp.database.exception;

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
