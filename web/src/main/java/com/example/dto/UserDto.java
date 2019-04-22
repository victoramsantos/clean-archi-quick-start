package com.example.dto;

import com.example.entities.User;
import com.example.exception.DataValidationException;
import com.example.exception.DtoParserException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;

    public static Set<UserDto> fromEntity(Set<User> users) {
        return users
                .stream()
                .map(UserDto::fromEntity)
                .collect(toSet());
    }

    public static UserDto fromEntity(User user) {
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    public Boolean sanitize() throws DtoParserException {
        if(username == null) throw new DtoParserException("Username can't be null");
        if(password == null) throw new DtoParserException("Password can't be null");

        return true;
    }
}

