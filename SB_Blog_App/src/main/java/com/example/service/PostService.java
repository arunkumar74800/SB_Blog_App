package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.binding.AddPostForm;
import com.example.entity.PostEntity;
import com.example.entity.UserEntity;
import com.example.request.SearchRequest;

public interface PostService {

    public void addPost(AddPostForm form, UserEntity user);

    Optional<PostEntity> getPostById(Integer postId);

    public List<PostEntity> searchPosts(SearchRequest searchRequest);

   

}
