package com.example.binding;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter 
public class AddPostForm {

    private String title;
    private String description;
    private String content;
    private String createdOn;
    private String updatedOn;

}
