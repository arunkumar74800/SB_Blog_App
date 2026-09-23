package com.example.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@Table (name = "COMMENT_TBL")
public class CommentEntity {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String name;
    private String email;
    private String title;
    @Lob 
    private String comment;
    @CreationTimestamp 
    private LocalDate createdOn;

    //soft delete krne ke liye
    private boolean isDeleted = false;

    @ManyToOne 
    @JoinColumn (name = "post_id")
    private PostEntity post;

}
