package com.example.service;

import com.example.repo.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binding.LoginForm;
import com.example.binding.SignUpForm;
import com.example.entity.UserEntity;

@Service 
public class UserServiceImpl implements UserService{

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private HttpSession session;


    @Override
    public boolean signUp(SignUpForm form) {
        UserEntity user = userRepository.findByEmail(form.getEmail());
        if (user != null) {
            return false;
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(form, entity);

        userRepository.save(entity);
        return true;
    }

    @Override
    public String login(LoginForm form) {
        UserEntity entity = userRepository.findByEmailAndPassword(form.getEmail(), form.getPassword());
        if (entity == null) {
            return "Invalid Credintials";
        }
        session.setAttribute("userId", entity.getUserId());
        return "success";
    }

}
