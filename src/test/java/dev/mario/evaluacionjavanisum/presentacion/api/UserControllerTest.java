package dev.mario.evaluacionjavanisum.presentacion.api;

import dev.mario.evaluacionjavanisum.domain.model.UserDom;
import dev.mario.evaluacionjavanisum.domain.port.primary.UserService;
import dev.mario.evaluacionjavanisum.presentation.api.controller.UserController;
import dev.mario.evaluacionjavanisum.presentation.api.model.UserRequest;
import dev.mario.evaluacionjavanisum.presentation.api.model.UserResponse;
import dev.mario.evaluacionjavanisum.presentation.api.model.valitation.Validation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private Validation validation;

    @Test
    void getAllUsers_returnsOk() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void createUser_returnsCreated() throws Exception {
        Mockito.doNothing().when(validation).isValidEmail(any());
        Mockito.doNothing().when(validation).isValidPassword(any());
        Mockito.when(userService.createUser(any(UserDom.class)))
                .thenReturn(UserResponse.builder().build());

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Juan Rodriguez II\",\n" +
                                "    \"email\": \"juan@host.cl\",\n" +
                                "    \"password\": \"H@hunter2\",\n" +
                                "    \"phones\": [\n" +
                                "        {\n" +
                                "            \"number\": \"1234567\",\n" +
                                "            \"citycode\": \"1\",\n" +
                                "            \"contrycode\": \"57\"\n" +
                                "        },\n" +
                                "                {\n" +
                                "            \"number\": \"1234567000\",\n" +
                                "            \"citycode\": \"01\",\n" +
                                "            \"contrycode\": \"570\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser_returnsNoContent() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/user/" + id))
                .andExpect(status().isOk());
    }
}