package com.example.entities;

import lombok.*;

@Builder
@ToString
@EqualsAndHashCode(of="id")
public class Book {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String author;
    @Getter @Setter private String synopsis;
    @Getter @Setter private String isbn;
}
