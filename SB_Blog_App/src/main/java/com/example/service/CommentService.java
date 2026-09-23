package com.example.service;

public interface CommentService {
    public void saveComment(Integer postId, String name, String email, String comments);

    public void softDeleteComment(Integer id);
}
