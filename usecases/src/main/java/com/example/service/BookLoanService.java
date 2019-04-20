package com.example.service;

import com.example.entities.BookLoan;
import com.example.exception.DataValidationException;
import com.example.exception.ElementNotFoundException;
import com.example.repository.DataAccess;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookLoanService {

    private DataAccess<BookLoan> dataAccess;
    public BookLoanService(DataAccess<BookLoan> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public BookLoan save(BookLoan elem) throws DataValidationException {
        //TODO Check the amount of books before make the loan
        return dataAccess.save(elem);
    }

    public void delete(Long id) throws DataValidationException {
        BookLoan found = dataAccess
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("No book loan found with id " + id));

        dataAccess.delete(found);
    }

    public BookLoan findById(Long id) throws DataValidationException {
        return dataAccess
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("No book loan found with id " + id));
    }

    public Set<BookLoan> list() {
        return dataAccess.list();
    }
}
