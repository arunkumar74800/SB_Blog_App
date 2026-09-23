package com.example.controller;

import com.example.repo.CommentRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.CommentEntity;
import com.example.entity.PostEntity;
import com.example.repo.PostRepository;
import com.example.service.CommentService;
import com.example.service.PostService;

@Controller 
public class HomeController {

    private final CommentRepository commentRepository;

    @Autowired 
    private PostRepository postRepository;

    @Autowired 
    private PostService postService;

    @Autowired 
    private CommentService commentService;

    HomeController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    public String indexPage(Model model){
        List<PostEntity> listPosts = postRepository.findAll();
        model.addAttribute("listPosts", listPosts);
        return "index";
    }

    @GetMapping ("/post/{id}")
    public String viewPostDetail(@PathVariable Integer id, Model model){
        Optional<PostEntity> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent()) {
            PostEntity post = optionalPost.get();
            model.addAttribute("post", post);
            return "post-details";
        }
        return "redirect:/";
    }

    @PostMapping ("/post/{id}/comment")
    public String addComment(@PathVariable Integer id,
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String comment){


        commentService.saveComment(id, name, email, comment);
        return "redirect:/post/" +id;
    }

    @GetMapping ("/admin/comments")
    public String viewAllomments(Model model){
        //Databse se saare comments fetch kre
        List<CommentEntity> comments = commentRepository.findByIsDeletedFalse();
        //model me attributr add kre
        model.addAttribute("comments", comments);
        //comment.html page ko return kre
        return "comment";
    }

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam ("id") Integer id){
        // List<CommentEntity> comments = commentRepository.findByIsDeletedFalse();
        commentService.softDeleteComment(id);
        return "redirect:/admin/comments";
    }

    @GetMapping ("/search")
    public String searchPosts(@RequestParam ("keyword") String keyword, Model model){
        List<PostEntity> searchPosts = postRepository.searchPosts(keyword);
        if(searchPosts != null && !searchPosts.isEmpty()){
            model.addAttribute("post", searchPosts.get(0));
            return "post-details";
        }
        return "redirect:/";
    }

}
