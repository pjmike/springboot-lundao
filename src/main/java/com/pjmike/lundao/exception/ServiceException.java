package com.pjmike.lundao.exception;

/**
 * @author pjmike
 * @create 2018-05-17 17:59
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
