package com.project.finalproject.exception.handler;

public class ObjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg){
        super(msg + " não encontrado.");
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
