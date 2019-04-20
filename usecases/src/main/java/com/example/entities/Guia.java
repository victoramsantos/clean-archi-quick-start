package com.example.entities;

import lombok.*;

@Builder
@ToString
@EqualsAndHashCode(of="id")
public class Guia {
    @Getter private Long id;
    @Getter @Setter private String name;
}
