package com.example.dto;

import com.example.entities.BookLoan;
import com.example.entities.LoanStatus;
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
public class BookLoanDto {
    private Long id;
    private GuiaDto guia;
    private Long guiaId;
    private BookDto book;
    private Long bookId;
    private String createdAt;
    private String status;

    public static Set<BookLoanDto> fromEntity(Set<BookLoan> books) {
        return books
                .stream()
                .map(BookLoanDto::fromEntity)
                .collect(toSet());
    }

        public static BookLoanDto fromEntity(BookLoan elem) {
        return builder()
                .id(elem.getId())
                .guiaId(elem.getGuia().getId())
                .guia(GuiaDto.fromEntity(elem.getGuia()))
                .bookId(elem.getBook().getId())
                .book(BookDto.fromEntity(elem.getBook()))
                .status(elem.getStatus().toString())
                .createdAt(elem.getCreatedAt())
                .build();
    }

    public BookLoan toEntity() {
        return BookLoan.builder()
                .id(id)
                .guia(guia.toEntity())
                .book(book.toEntity())
                .createdAt(createdAt)
                .status(LoanStatus.valueOf(status))
                .build();
    }

    public Boolean sanitize() throws DtoParserException {
        if(guiaId == null) throw new DtoParserException("Guia can't be null");
        if(bookId == null) throw new DtoParserException("Book can't be null");
        if(status == null) throw new DtoParserException("Status must be ONGOING or RETURNED");
        if(!createdAt.matches("\\d{4}-\\d{2}-\\d{2}")) throw new DtoParserException("Date must be in the pattern yyyy-mm-dd");

        return true;
    }
}
