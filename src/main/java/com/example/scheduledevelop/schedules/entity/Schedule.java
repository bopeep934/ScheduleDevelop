package com.example.scheduledevelop.schedules.entity;

import com.example.scheduledevelop.users.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="schedule")
public class Schedule extends BaseEntity{//일정 객체
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(columnDefinition="longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule( String title, String contents){
        this.title=title;
        this.contents=contents;
    }

    public Schedule() {

    }

    public void update(String title, String contents){
        this.title=title;
        this.contents=contents;
    }

}

