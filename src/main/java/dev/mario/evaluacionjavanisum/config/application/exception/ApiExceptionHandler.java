package dev.mario.evaluacionjavanisum.config.application.exception;

import dev.mario.evaluacionjavanisum.config.application.exception.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({
            UserExistsException.class,
    })
    @ResponseBody
    public ErrorMessage userExist(Exception exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({
            EmailFormatException.class,
    })
    @ResponseBody
    public ErrorMessage emailFormat(Exception exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({
            PasswordFormatException.class,
    })
    @ResponseBody
    public ErrorMessage passwordFormat(Exception exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({
            UserNoExistsException.class,
    })
    @ResponseBody
    public ErrorMessage userNoExist(Exception exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class,
            RuntimeException.class,
            NullPointerException.class,
            IllegalArgumentException.class,
    })
    @ResponseBody
    public ErrorMessage handleGeneralException(Exception exception) {
        return new ErrorMessage("Ha ocurrido un error inesperado: " + exception.getMessage());
    }
}
