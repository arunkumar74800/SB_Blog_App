package com.example.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.PostEntity;
import com.example.entity.UserEntity;


public interface PostRepository extends JpaRepository<PostEntity, Integer>{
    List<PostEntity> findByUser(UserEntity user);

    List<PostEntity> findByIsDeletedFalse();

    @Query ("SELECT p FROM PostEntity p WHERE p.title LIKE CONCAT('%',?1,'%') OR p.content LIKE CONCAT('%',?1,'%')" )
    List<PostEntity> searchPosts(String keyword);
}


