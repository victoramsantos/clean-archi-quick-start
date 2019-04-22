package com.example.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode(of="id")
public class User {
    @Getter private Long id;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
}
