package dev.mario.evaluacionjavanisum.presentation.api.model.valitation;

import dev.mario.evaluacionjavanisum.config.application.exception.model.EmailFormatException;
import dev.mario.evaluacionjavanisum.config.application.exception.model.PasswordFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    private final String emailRegex;
    private final String passwordRegex;

    public Validation(@Value("${validation.email.regex}") String emailRegex,
                      @Value("${validation.password.regex}") String passwordRegex) {
        this.emailRegex = emailRegex;
        this.passwordRegex = passwordRegex;
    }

    // validate email
    public void isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmailFormatException();
        }

        if (!email.matches(emailRegex)) {
            throw new EmailFormatException();
        }
    }

    // validate password
    public void isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new PasswordFormatException();
        }
        if (!password.matches(passwordRegex)) {
            throw new PasswordFormatException();
        }
    }
}
