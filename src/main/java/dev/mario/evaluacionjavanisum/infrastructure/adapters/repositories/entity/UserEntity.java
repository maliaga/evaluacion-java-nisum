// src/main/java/dev/mario/evaluacionjavanisum/infrastructure/adapters/repositories/entity/UserEntity.java
package dev.mario.evaluacionjavanisum.infrastructure.adapters.repositories.entity;

import dev.mario.evaluacionjavanisum.domain.model.UserDom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String email;
    private String password;

    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean active;
    private String token;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<PhoneEntity> phones;

    public static UserEntity from(UserDom user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(user.getCreated())
                .modified(user.getModified())
                .active(user.isActive())
                .token(user.getToken())
                .phones(PhoneEntity.fromList(user.getPhones()))
                .build();
    }
}