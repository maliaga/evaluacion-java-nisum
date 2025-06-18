package dev.mario.evaluacionjavanisum.config.application.exception.model;

public class UserNoExistsException extends RuntimeException{
    private static final String DESCRIPTION = "El usuario no existe";

    public UserNoExistsException(){
        super(DESCRIPTION);
    }
}
