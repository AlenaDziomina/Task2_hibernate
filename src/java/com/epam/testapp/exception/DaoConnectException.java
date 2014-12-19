package com.epam.testapp.exception;

/**
 *
 * @author Helena.Grouk
 */
public class DaoConnectException extends Exception  {

    public DaoConnectException(){
    }

    public DaoConnectException(String message, Throwable exception) {
        super(message, exception);
    }

    public DaoConnectException(Throwable exception) {
        super(exception);
    }

    public DaoConnectException(String message) {
        super(message);
    }
}
