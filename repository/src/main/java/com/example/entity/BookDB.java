package com.example.entity;

import com.example.entities.Book;
import lombok.*;

import javax.persistence.*;

@Builder
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name="book_generator", sequenceName = "book_seq")
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String author;
    @Getter @Setter private String synopsis;
    @Getter @Setter private String isbn;

    public Book toEntity() {
        return Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .synopsis(synopsis)
                .isbn(isbn)
                .build();
    }

    public static BookDB fromEntity(Book elem) {
        return builder()
                .id(elem.getId())
                .name(elem.getName())
                .author(elem.getAuthor())
                .synopsis(elem.getSynopsis())
                .isbn(elem.getIsbn())
                .build();
    }
}
