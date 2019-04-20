package com.example.service;

import com.example.exception.DataValidationException;

import java.util.Set;

public interface GenericService<T> {
    T save(T elem) throws DataValidationException;
    T update(T elem) throws DataValidationException;
    void delete(Long id) throws DataValidationException;
    T findById(Long id) throws DataValidationException;
    Set<T> list();
}
