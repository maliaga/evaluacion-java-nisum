package dev.mario.evaluacionjavanisum.domain.usecase;

import dev.mario.evaluacionjavanisum.config.application.JwtService;
import dev.mario.evaluacionjavanisum.config.application.exception.model.UserExistsException;
import dev.mario.evaluacionjavanisum.config.application.exception.model.UserNoExistsException;
import dev.mario.evaluacionjavanisum.domain.model.PhoneDom;
import dev.mario.evaluacionjavanisum.domain.model.UserDom;
import dev.mario.evaluacionjavanisum.domain.port.secundary.UserPort;
import dev.mario.evaluacionjavanisum.infrastructure.adapters.repositories.entity.UserEntity;
import dev.mario.evaluacionjavanisum.presentation.api.model.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserPort userPort;
    private JwtService jwtService;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userPort = mock(UserPort.class);
        jwtService = mock(JwtService.class);
        userService = new UserServiceImpl(userPort, jwtService);
    }

    @Test
    void getAllUsers_returnsList() {
        UserEntity entity = new UserEntity();
        when(userPort.findAll()).thenReturn(List.of(entity));
        List<UserResponse> result = userService.getAllUsers();
        assertEquals(1, result.size());
    }

    @Test
    void createUser_userExists_throwsException() {
        UserDom user = new UserDom();
        user.setEmail("test@mail.com");
        when(userPort.existsByEmail("test@mail.com")).thenReturn(true);
        assertThrows(UserExistsException.class, () -> userService.createUser(user));
    }

    @Test
    void createUser_success_returnsUserResponse() {
        UserDom user = new UserDom();
        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPhones(List.of(new PhoneDom(), new PhoneDom()));

        when(userPort.existsByEmail("test@mail.com")).thenReturn(false);
        when(jwtService.generarToken("Test")).thenReturn("token123");
        when(userPort.save(any(UserEntity.class))).thenReturn(new UserEntity());
        UserResponse response = userService.createUser(user);
        assertNotNull(response);
        assertEquals("token123", user.getToken());
    }

    @Test
    void deleteUser_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(null));
    }

    @Test
    void deleteUser_userNoExists_throwsException() {
        UUID id = UUID.randomUUID();
        when(userPort.existsById(id)).thenReturn(false);
        assertThrows(UserNoExistsException.class, () -> userService.deleteUser(id));
    }

    @Test
    void deleteUser_success_callsDelete() {
        UUID id = UUID.randomUUID();
        when(userPort.existsById(id)).thenReturn(true);
        userService.deleteUser(id);
        verify(userPort).deleteById(id);
    }
}
