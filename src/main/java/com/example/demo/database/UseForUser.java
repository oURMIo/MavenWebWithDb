package com.example.demo.database;

import org.springframework.data.repository.CrudRepository;

public interface UseForUser extends CrudRepository<User, Long> {
}