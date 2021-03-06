package com.example.service;

import com.example.entities.Book;
import com.example.exception.BusinessInvalidException;
import com.example.exception.DataValidationException;
import com.example.exception.EmptyParameterException;
import com.example.exception.ElementNotFoundException;
import com.example.repository.DataAccess;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookService{

    private DataAccess<Book> dataAccess;
    public BookService(DataAccess<Book> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public Book save(Book elem) throws DataValidationException {
        if(elem.getName().isEmpty()) throw new EmptyParameterException("Book can't has an empty name");
        if(elem.getAuthor().isEmpty()) throw new EmptyParameterException("Book can't has an empty author");
        if(elem.getSynopsis().isEmpty()) throw new EmptyParameterException("Book can't has an empty synopsis");
        if(elem.getIsbn().isEmpty()) throw new EmptyParameterException("Book can't has an empty isbn");

        if(elem.getAmount() < 0) throw new BusinessInvalidException("Amount must be grater or equal to 0");
        if(!elem.getAmount().equals(elem.getAvailable())) throw new BusinessInvalidException("The availability must be equals to amount");

        return dataAccess.save(elem);
    }

    public Book update(Book elem) throws DataValidationException {
        Book found = dataAccess
                .findById(elem.getId())
                .orElseThrow(() -> new ElementNotFoundException("Book not found with id " + elem.getId()));

        found.setName(elem.getName());
        found.setAuthor(elem.getAuthor());
        found.setSynopsis(elem.getSynopsis());
        found.setIsbn(elem.getIsbn());

        return dataAccess.save(found);
    }

    public void delete(Long id) throws DataValidationException {
        Book found = dataAccess
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Book not found with id " + id));

        dataAccess.delete(found);
    }

    public Book findById(Long id) throws DataValidationException {
        return dataAccess
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Book not found with id " + id));
    }

    public Set<Book> list() {
        return dataAccess.list();
    }

    public Book makeLoan(Book book) throws DataValidationException {
        if(book.getAvailable() == 0) throw new BusinessInvalidException("There is no available book");
        book.makeLoan();

        return dataAccess.save(book);
    }

    public Book makeReturn(Book book) {
        book.makeReturn();

        return dataAccess.save(book);
    }
}
