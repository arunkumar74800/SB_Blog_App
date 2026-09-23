package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CommentEntity;
import com.example.entity.PostEntity;
import com.example.repo.CommentRepository;
import com.example.repo.PostRepository;

@Service 
public class CommentServiceImpl implements CommentService {

    @Autowired 
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void saveComment(Integer postId, String name, String email, String comments) {
        PostEntity post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            CommentEntity comment = new CommentEntity();
            //database wala post ka title yaha automatically set kar diye
            comment.setTitle(post.getTitle());
            comment.setName(name);
            comment.setEmail(email);
            comment.setComment(comments);
            //relation map kiye
            comment.setPost(post);

            commentRepository.save(comment);
        }else{
            throw new RuntimeException("Post not found: " +postId);
        }
    }

    @Override
    public void softDeleteComment(Integer id) {
        CommentEntity comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setDeleted(true);
            commentRepository.save(comment);
        }
    }

    

}
