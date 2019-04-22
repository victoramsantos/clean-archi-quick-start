package com.example.impl;

import com.example.entities.User;
import com.example.entity.UserDB;
import com.example.repository.DataAccess;
import com.example.repository.UserDataAcess;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserDataAcessImpl implements UserDataAcess {

    private UserRepository repository;
    public UserDataAcessImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User elem) {
        UserDB db = UserDB.fromEntity(elem);
        return repository.save(db).toEntity();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository
                .findById(id)
                .map(UserDB::toEntity);
    }

    @Override
    public Set<User> findByUsername(String username) {
        Set<User> set = new HashSet();

        repository
                .findByUsername(username)
                .forEach(db -> set.add(db.toEntity()));
        return set;
    }

    @Override
    public void delete(User elem) {
        UserDB db = UserDB.fromEntity(elem);
        repository.delete(db);
    }

    @Override
    public Set<User> list() {
        Set<User> set = new HashSet();

        repository
                .findAll()
                .forEach(db -> set.add(db.toEntity()));

        return set;
    }
}
