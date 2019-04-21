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
    @Getter @Setter private Integer amount;
    @Getter @Setter private Integer available;

    public void makeLoan() {
        this.available -= 1;
    }

    public void makeReturn() {
        this.available += 1;
    }
}
