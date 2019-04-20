package com.example.service;

import com.example.entities.Guia;
import com.example.exception.EmptyParameterException;
import com.example.exception.ElementNotFoundException;
import com.example.repository.DataAccess;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GuiaService {

    private DataAccess<Guia> dataAccess;
    public GuiaService(DataAccess<Guia> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public Guia save(Guia guia) throws EmptyParameterException {
        if(guia.getName().isEmpty()) throw new EmptyParameterException("Guia can't has an empty name");

        return dataAccess.save(guia);
    }

    public Guia update(Guia guia) throws ElementNotFoundException {
        Guia found = dataAccess
                .findById(guia.getId())
                .orElseThrow(() -> new ElementNotFoundException("guia not found with id " + guia.getId()));

        found.setName(guia.getName());

        return dataAccess.save(found);
    }

    public void delete(Long id) throws ElementNotFoundException {
        Guia found = dataAccess.
                findById(id)
                .orElseThrow(() -> new ElementNotFoundException("guia not found with id " + id));

        dataAccess.delete(found);
    }

    public Guia findById(Long id) throws ElementNotFoundException {
        return dataAccess
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("guia not found with id " + id));
    }

    public Set<Guia> list(){
        return dataAccess.list();
    }
}
