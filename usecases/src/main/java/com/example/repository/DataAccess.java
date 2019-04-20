package com.example.repository;

import com.example.entities.Guia;

import java.util.Optional;
import java.util.Set;

public interface DataAccess<T> {
    T save(T elem);
    Optional<T> findById(Long id);
    void delete(T elem);
    Set<T> list();
}
