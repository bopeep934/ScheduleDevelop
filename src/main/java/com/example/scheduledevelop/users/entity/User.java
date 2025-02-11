package com.example.scheduledevelop.users.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="user")
public class User extends BaseEntity{//유저 객체

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String username;

    @Column(nullable=false)
    private String email;

    public User(String username, String email){
        this.username=username;
        this.email=email;
    }

    public User() {

    }

    public void update(String username, String email){
        this.username=username;
        this.email=email;
    }
}
