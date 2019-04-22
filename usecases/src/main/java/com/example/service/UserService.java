package com.example.service;

import com.example.entities.Book;
import com.example.entities.User;
import com.example.exception.BusinessInvalidException;
import com.example.exception.DataValidationException;
import com.example.exception.ElementNotFoundException;
import com.example.exception.EmptyParameterException;
import com.example.repository.DataAccess;
import com.example.repository.UserDataAcess;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private UserDataAcess dataAccess;
    public UserService(UserDataAcess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public User save(User elem) throws DataValidationException {
        if(elem.getUsername().isEmpty()) throw new EmptyParameterException("User can't has an empty username");
        if(elem.getPassword().isEmpty()) throw new EmptyParameterException("User can't has an empty password");

        dataAccess.findByUsername(elem.getUsername());

        return dataAccess.save(elem);
    }

    public User findById(Long id) throws DataValidationException {
        return dataAccess
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("User not found with id " + id));
    }

    public Set<User> list() {
        return dataAccess.list();
    }

}
