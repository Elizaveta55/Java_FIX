package com.company.dto;

import com.company.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String login;
    private String role;

    public static UserDto from(User model){
        return UserDto.builder()
                .login(model.getLogin())
                .role(model.getRole().toString())
                .build();
    }
}
