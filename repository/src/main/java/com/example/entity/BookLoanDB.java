package com.example.entity;

import com.example.entities.Book;
import com.example.entities.BookLoan;
import com.example.entities.Guia;
import com.example.enums.LoanStatusDB;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
@Builder
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookLoanDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookloan_generator")
    @SequenceGenerator(name="bookloan_generator", sequenceName = "bookloan_seq")
    @Getter @Setter private Long id;

    @OneToOne
    @Getter @Setter private GuiaDB guia;

    @OneToOne
    @Getter @Setter private BookDB book;

    @CreatedDate
    @Getter @Setter private String createdAt;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Getter @Setter private LoanStatusDB status = LoanStatusDB.ONGOING;

    public BookLoan toEntity() {
        return BookLoan.builder()
                .id(id)
                .book(book.toEntity())
                .guia(guia.toEntity())
                .createdAt(createdAt)
                .status(LoanStatusDB.toEntity(status))
                .build();
    }

    public static BookLoanDB fromEntity(BookLoan elem) {
        return builder()
                .id(elem.getId())
                .book(BookDB.fromEntity(elem.getBook()))
                .guia(GuiaDB.fromEntity(elem.getGuia()))
                .createdAt(elem.getCreatedAt())
                .status(LoanStatusDB.fromEntity(elem.getStatus()))
                .build();
    }
}
