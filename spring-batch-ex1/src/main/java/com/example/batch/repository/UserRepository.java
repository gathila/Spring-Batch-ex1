package com.example.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.batch.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
