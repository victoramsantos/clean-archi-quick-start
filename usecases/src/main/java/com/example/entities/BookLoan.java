package com.example.entities;

import lombok.*;

import java.util.Date;

@Builder
@ToString
@EqualsAndHashCode(of="id")
public class BookLoan {
    @Getter @Setter private Long id;
    @Getter @Setter private Guia guia;
    @Getter @Setter private Book book;
    @Getter @Setter private String createdAt;
    @Builder.Default @Getter private LoanStatus status = LoanStatus.ONGOING;

    public void setToInactive() {
        status = LoanStatus.RETURNED;
    }
}
