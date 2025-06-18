package dev.mario.evaluacionjavanisum.domain.usecase;

import dev.mario.evaluacionjavanisum.config.application.JwtService;
import dev.mario.evaluacionjavanisum.config.application.exception.model.UserExistsException;
import dev.mario.evaluacionjavanisum.config.application.exception.model.UserNoExistsException;
import dev.mario.evaluacionjavanisum.domain.model.UserDom;
import dev.mario.evaluacionjavanisum.domain.port.primary.UserService;
import dev.mario.evaluacionjavanisum.domain.port.secundary.UserPort;
import dev.mario.evaluacionjavanisum.infrastructure.adapters.repositories.entity.UserEntity;
import dev.mario.evaluacionjavanisum.presentation.api.model.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPort userPort;
    private final JwtService jwtService;

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        return userPort.findAll().stream()
                .map(UserResponse::from)
                .toList();
    }

    @Override
    public UserResponse createUser(UserDom user) {
        log.info("Creating user: {}", user);

        if (userPort.existsByEmail(user.getEmail())) {
            log.warn("User with email {} already exists", user.getEmail());
            throw new UserExistsException();
        }

        String jwtToken = jwtService.generarToken(user.getName());
        log.info("Generated JWT token: {}", jwtToken);
        user.setToken(jwtToken);

        return UserResponse.from(userPort.save(UserEntity.from(user)));
    }

    @Override
    public void deleteUser(UUID id) {
    if (id == null) {
            log.error("User ID is null, cannot delete user");
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (!userPort.existsById(id)) {
            log.warn("User with id {} does not exist", id);
            throw new UserNoExistsException();
        }
        log.info("Eliminando usuario con id: {}", id);
        userPort.deleteById(id);
    }


}
