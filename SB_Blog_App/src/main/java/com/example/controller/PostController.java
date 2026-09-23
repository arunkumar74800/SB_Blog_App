package com.example.controller;

import com.example.repo.PostRepository;
import com.example.repo.UserRepository;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.binding.AddPostForm;
import com.example.entity.PostEntity;
import com.example.entity.UserEntity;
import com.example.service.PostService;

import jakarta.servlet.http.HttpSession;



@Controller 
public class PostController {

    private final PostRepository postRepository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private PostService postService;

    @Autowired 
    private HttpSession session;


    PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping ("/addPost")
    public String savePost(Model model){
        model.addAttribute("post", new AddPostForm());
        return "createdpost";
    }

    @PostMapping ("/savePost")
    public String handleSavePost(@ModelAttribute ("post") AddPostForm form,
     Model model, HttpSession session){
    
        String email = (String)session.getAttribute("email");
        UserEntity user = userRepository.findByEmail(email);
        
        postService.addPost(form, user);
        return "redirect:/dashboard";
    }

    @GetMapping ("/dashboard")
    public String dashboard(Model model){
        List<PostEntity> posts = postRepository.findByIsDeletedFalse();
        model.addAttribute("postLists", posts);
        return "dashboard";
    }

    @GetMapping ("/editPost")
    public String editPost(@RequestParam (value = "id", required=false) Integer id, Model model){
        if(id == null){
            return "redirect:/dashboard";
        }
        PostEntity post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "editpost";
    }

    @PostMapping ("/updatePost")
    public String updatePost(@ModelAttribute ("post") PostEntity post){
        postRepository.save(post);
        return "redirect:/dashboard";
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam ("id") Integer id){
        PostEntity post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setDeleted(true);
            postRepository.save(post); //Databse me update kar diya
        }
        return "redirect:/dashboard";
    }

}
