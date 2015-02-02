package com.epam.testapp.database.exception;

public class DaoException extends Exception {

    public DaoException(){
    }

    public DaoException(String message, Throwable exception) {
        super(message, exception);
    }

    public DaoException(Throwable exception) {
        super(exception);
    }

    public DaoException(String message) {
        super(message);
    }
}
