package com.example.repository;

import com.example.entities.User;

import java.util.Set;

public interface UserDataAcess extends DataAccess<User> {
    Set<User> findByUsername(String username);
}
