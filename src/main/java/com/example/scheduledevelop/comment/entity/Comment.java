package com.example.scheduledevelop.comment.entity;

import com.example.scheduledevelop.schedules.entity.Schedule;
import com.example.scheduledevelop.users.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="comments")
public class Comment extends BaseEntity{

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String nickname;

    @Column(nullable=false)
    private String comment;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    public Comment(String nickname, String comment , Schedule schedule){
        this.nickname=nickname;
        this.comment=comment;
        this.schedule=schedule;
    }

    public Comment() {

    }

    public void update(String nickname, String comment) {
        this.nickname=nickname;
        this.comment=comment;
    }
}
