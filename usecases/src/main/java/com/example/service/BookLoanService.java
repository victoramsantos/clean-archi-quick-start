package com.example.service;

import com.example.entities.BookLoan;
import com.example.exception.BusinessInvalidException;
import com.example.exception.DataValidationException;
import com.example.exception.ElementNotFoundException;
import com.example.repository.DataAccess;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookLoanService {

    private DataAccess<BookLoan> dataAccess;
    private BookService bookService;
    public BookLoanService(DataAccess<BookLoan> dataAccess, BookService bookService) {
        this.dataAccess = dataAccess;
        this.bookService = bookService;
    }

    public BookLoan save(BookLoan elem) throws DataValidationException {
        elem.setBook(bookService.makeLoan(elem.getBook()));
        return dataAccess.save(elem);
    }

    public BookLoan updateStatus(Long id) throws DataValidationException {
        BookLoan found = dataAccess
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("No book loan found with id " + id));

        if(!found.isUpdatable()) throw new BusinessInvalidException("This loan has been returned");

        found.setBook(bookService.makeReturn(found.getBook()));
        found.makeReturn();
        return dataAccess.save(found);
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
