package com.example.impl;

import com.example.entities.Guia;
import com.example.entity.GuiaDB;
import com.example.repository.DataAccess;
import com.example.repository.GuiaRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class GuiaDataAccessImpl implements DataAccess<Guia> {

    private GuiaRepository guiaRepository;

    public GuiaDataAccessImpl(GuiaRepository guiaRepository) {
        this.guiaRepository = guiaRepository;
    }

    @Override
    public Guia save(Guia guia) {
        GuiaDB db = GuiaDB.fromEntity(guia);
        return guiaRepository.save(db).toEntity();
    }

    @Override
    public void delete(Guia guia) {
        GuiaDB db = GuiaDB.fromEntity(guia);
        guiaRepository.delete(db);
    }

    @Override
    public Optional<Guia> findById(Long id) {
        return guiaRepository
                .findById(Long.valueOf(id))
                .map(GuiaDB::toEntity);
    }

    @Override
    public Set<Guia> list() {
        Set<Guia> set = new HashSet<Guia>();

        guiaRepository
            .findAll()
            .forEach(guiaDB -> set.add((guiaDB.toEntity())));

        return set;
    }
}
