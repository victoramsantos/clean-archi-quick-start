package com.example.impl;

import com.example.entities.Book;
import com.example.entity.BookDB;
import com.example.repository.DataAccess;
import com.example.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class BookDataAcessImpl implements DataAccess<Book> {

    private BookRepository repository;
    public BookDataAcessImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book elem) {
        BookDB db = BookDB.fromEntity(elem);
        return repository.save(db).toEntity();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository
                .findById(id)
                .map(BookDB::toEntity);
    }

    @Override
    public void delete(Book elem) {
        BookDB db = BookDB.fromEntity(elem);
        repository.delete(db);
    }

    @Override
    public Set<Book> list() {
        Set<Book> set = new HashSet();

        repository
                .findAll()
                .forEach(db -> set.add(db.toEntity()));

        return set;
    }
}
