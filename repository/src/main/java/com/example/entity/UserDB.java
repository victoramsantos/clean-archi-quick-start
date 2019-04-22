package com.example.entity;

import com.example.entities.User;
import com.example.enums.UserStatusDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static java.lang.Long.valueOf;
import static org.springframework.util.StringUtils.isEmpty;

@Builder
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDB {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "usr_seq")
    @Getter @Setter private Long id;
    @Getter @Setter private String username;
    @Getter @Setter private String password;


    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    public static UserDB fromEntity(User user) {
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}

