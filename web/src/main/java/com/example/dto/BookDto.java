package com.example.dto;

import com.example.entities.Book;
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
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private String synopsis;
    private String isbn;
    private Integer amount;
    private Integer available;

    public static Set<BookDto> fromEntity(Set<Book> books) {
        return books
                .stream()
                .map(BookDto::fromEntity)
                .collect(toSet());
    }

    public static BookDto fromEntity(Book book) {
        return builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .synopsis(book.getSynopsis())
                .isbn(book.getIsbn())
                .amount(book.getAmount())
                .available(book.getAvailable())
                .build();
    }

    public Book toEntity() {
        return Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .synopsis(synopsis)
                .isbn(isbn)
                .amount(amount)
                .available(available)
                .build();
    }

    public Boolean sanitize() throws DtoParserException {
        if(name == null) throw new DtoParserException("Name can't be null");
        if(author == null) throw new DtoParserException("Author can't be null");
        if(synopsis == null) throw new DtoParserException("Synopsis can't be null");
        if(isbn == null) throw new DtoParserException("Isbn can't be null");
        if(amount == null) throw new DtoParserException("Amount can't be null");
        if(available == null) throw new DtoParserException("Available can't be null");
        return true;
    }
}
