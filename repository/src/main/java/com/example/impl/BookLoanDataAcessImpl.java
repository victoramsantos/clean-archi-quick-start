package com.example.impl;

import com.example.entities.BookLoan;
import com.example.entity.BookLoanDB;
import com.example.repository.BookLoanRepository;
import com.example.repository.DataAccess;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class BookLoanDataAcessImpl implements DataAccess<BookLoan> {


    private BookLoanRepository repository;
    public BookLoanDataAcessImpl(BookLoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookLoan save(BookLoan elem) {
        BookLoanDB db = BookLoanDB.fromEntity(elem);
        return repository.save(db).toEntity();
    }

    @Override
    public Optional<BookLoan> findById(Long id) {
        return repository
                .findById(id)
                .map(BookLoanDB::toEntity);
    }

    @Override
    public void delete(BookLoan elem) {
        BookLoanDB db = BookLoanDB.fromEntity(elem);
        repository.delete(db);
    }

    @Override
    public Set<BookLoan> list() {
        Set<BookLoan> set = new HashSet();

        repository
                .findAll()
                .forEach(db -> set.add(db.toEntity()));

        return set;
    }
}
