package dev.mario.evaluacionjavanisum.presentacion.api.model.validation;

import dev.mario.evaluacionjavanisum.config.application.exception.model.EmailFormatException;
import dev.mario.evaluacionjavanisum.config.application.exception.model.PasswordFormatException;
import dev.mario.evaluacionjavanisum.presentation.api.model.valitation.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationTest {
    private Validation validation;

    @BeforeEach
    void setUp() {
        // Regex de ejemplo, ajústalos según tu config real
        validation = new Validation("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", "^(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

    @Test
    void isValidEmail_validEmail_noException() {
        assertDoesNotThrow(() -> validation.isValidEmail("test@mail.com"));
    }

    @Test
    void isValidEmail_null_throwsException() {
        assertThrows(EmailFormatException.class, () -> validation.isValidEmail(null));
    }

    @Test
    void isValidEmail_empty_throwsException() {
        assertThrows(EmailFormatException.class, () -> validation.isValidEmail(""));
    }

    @Test
    void isValidEmail_invalidFormat_throwsException() {
        assertThrows(EmailFormatException.class, () -> validation.isValidEmail("invalid-email"));
    }

    @Test
    void isValidPassword_validPassword_noException() {
        assertDoesNotThrow(() -> validation.isValidPassword("Abcdefg1"));
    }

    @Test
    void isValidPassword_null_throwsException() {
        assertThrows(PasswordFormatException.class, () -> validation.isValidPassword(null));
    }

    @Test
    void isValidPassword_empty_throwsException() {
        assertThrows(PasswordFormatException.class, () -> validation.isValidPassword(""));
    }

    @Test
    void isValidPassword_invalidFormat_throwsException() {
        assertThrows(PasswordFormatException.class, () -> validation.isValidPassword("abcdefg"));
    }

}
