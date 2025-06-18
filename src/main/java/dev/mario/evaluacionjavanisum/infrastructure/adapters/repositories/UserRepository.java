package dev.mario.evaluacionjavanisum.infrastructure.adapters.repositories;

import dev.mario.evaluacionjavanisum.infrastructure.adapters.repositories.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
}
