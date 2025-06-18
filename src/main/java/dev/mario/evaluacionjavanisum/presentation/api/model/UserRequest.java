package dev.mario.evaluacionjavanisum.presentation.api.model;

import dev.mario.evaluacionjavanisum.domain.model.ACTIVE;
import dev.mario.evaluacionjavanisum.domain.model.UserDom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private ArrayList<PhoneRequest> phones;

    public static UserDom toDomain(UserRequest userRequest) {
        return UserDom.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(PhoneRequest.toDomainList(userRequest.getPhones()))
                .active(ACTIVE.YES.getValue())
                .created(LocalDateTime.now())
                .build();
    }
}
