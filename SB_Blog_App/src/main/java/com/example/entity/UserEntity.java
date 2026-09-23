package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Setter 
@Getter 
@Table (name = "USER_TBL")
public class UserEntity {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private String password;

    //This 2 line not mandatory
    // @OneToMany (mappedBy =  "user", cascade = CascadeType.REMOVE)
    // private List<Post> posts;

}
