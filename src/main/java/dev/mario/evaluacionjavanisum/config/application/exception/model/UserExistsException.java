package dev.mario.evaluacionjavanisum.config.application.exception.model;

public class UserExistsException extends RuntimeException{
    private static final String DESCRIPTION = "El correo ya registrado";

    public UserExistsException(){
        super(DESCRIPTION);
    }
}
