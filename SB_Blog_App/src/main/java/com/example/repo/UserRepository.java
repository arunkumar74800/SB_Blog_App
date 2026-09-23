package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    public UserEntity findByEmail(String email);

    public UserEntity findByEmailAndPassword(String email, String password);
}
