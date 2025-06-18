package dev.mario.evaluacionjavanisum.config.application.exception.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    @JsonProperty("mensaje")
    private String message;
}
