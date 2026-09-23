package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binding.AddPostForm;
import com.example.entity.PostEntity;
import com.example.entity.UserEntity;
import com.example.repo.PostRepository;
import com.example.request.SearchRequest;

@Service 
public class PostServiceImpl implements PostService{

    @Autowired 
    private PostRepository postRepository;

    @Override
    public void addPost(AddPostForm form, UserEntity user) {
        PostEntity entity = new PostEntity();
        entity.setTitle(form.getTitle());
        entity.setContent(form.getContent());
        entity.setDescription(form.getDescription());
        entity.setUser(user);

        postRepository.save(entity);
    }

    @Override
    public Optional<PostEntity> getPostById(Integer postId) {
        return postRepository.findById(postId);
    }

    @Override
    public List<PostEntity> searchPosts(SearchRequest searchRequest) {
        return postRepository.searchPosts(
            searchRequest.getTitle()
        );
    }

    

}
