package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@Table (name = "blog_tbl")
public class PostEntity {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "post_id")
    private Integer postId;
    private String title;
    private String description;
    @Lob 
    private String content;
    @CreationTimestamp 
    private LocalDate createdOn;
    @UpdateTimestamp 
    private LocalDate updatedOn;

    //soft delete krne ke liye
    private boolean isDeleted = false;

    @ManyToOne 
    @JoinColumn (name = "user_id", referencedColumnName = "userId")
    private UserEntity user;

    @OneToMany (mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments;
}
