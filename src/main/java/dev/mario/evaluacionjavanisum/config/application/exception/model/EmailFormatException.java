package dev.mario.evaluacionjavanisum.config.application.exception.model;

public class EmailFormatException extends RuntimeException{
    private static final String DESCRIPTION = "El formato del email no es el correcto";

    public EmailFormatException(){
        super(DESCRIPTION);
    }
}
