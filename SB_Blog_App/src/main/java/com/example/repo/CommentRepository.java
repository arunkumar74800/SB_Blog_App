package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    List<CommentEntity> findByIsDeletedFalse();

}
