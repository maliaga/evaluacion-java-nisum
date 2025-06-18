package dev.mario.evaluacionjavanisum.presentation.api.controller;

import dev.mario.evaluacionjavanisum.domain.model.UserDom;
import dev.mario.evaluacionjavanisum.domain.port.primary.UserService;
import dev.mario.evaluacionjavanisum.presentation.api.model.UserRequest;
import dev.mario.evaluacionjavanisum.presentation.api.model.UserResponse;
import dev.mario.evaluacionjavanisum.presentation.api.model.valitation.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class UserController {

    public final UserService userService;
    private final Validation validation;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Create a new user
    @PostMapping("/user")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        log.info("Creating user: {}", userRequest);

        // Validate email
        validation.isValidEmail(userRequest.getEmail());

        // Validate password
        validation.isValidPassword(userRequest.getPassword());

        UserDom userdom = UserRequest.toDomain(userRequest);

        log.info("Creating user dom: {}", userdom);

        return userService.createUser(userdom);
    }

    // Delete a user by ID
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        log.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
    }
}
