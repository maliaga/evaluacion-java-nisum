package dev.mario.evaluacionjavanisum.config.application.exception.model;

public class PasswordFormatException extends RuntimeException {
    private static final String DESCRIPTION = "La contraseña debe contener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.";

    public PasswordFormatException(){
        super(DESCRIPTION);
    }
}
