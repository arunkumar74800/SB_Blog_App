package com.example.service;

import com.example.binding.LoginForm;
import com.example.binding.SignUpForm;

public interface UserService {

    public boolean signUp(SignUpForm form);

    public String login(LoginForm form);
    
}
