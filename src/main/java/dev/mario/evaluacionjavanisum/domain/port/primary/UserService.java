package dev.mario.evaluacionjavanisum.domain.port.primary;

import dev.mario.evaluacionjavanisum.domain.model.UserDom;
import dev.mario.evaluacionjavanisum.presentation.api.model.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse createUser(UserDom user);
    void deleteUser(UUID id);

}
