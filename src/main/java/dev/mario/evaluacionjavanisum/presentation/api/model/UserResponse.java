package dev.mario.evaluacionjavanisum.presentation.api.model;

import dev.mario.evaluacionjavanisum.infrastructure.adapters.repositories.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean active;
    private String token;

    public static UserResponse from(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(user.getCreated())
                .modified(user.getModified())
                .active(user.isActive())
                .token(user.getToken())
                .build();
    }
}
