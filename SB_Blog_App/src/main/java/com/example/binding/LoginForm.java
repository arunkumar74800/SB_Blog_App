package com.example.binding;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter 
public class LoginForm {
    private String email;
    private String password;
}
