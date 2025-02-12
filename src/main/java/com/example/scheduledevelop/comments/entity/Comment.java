package com.example.scheduledevelop.comments.entity;

import com.example.scheduledevelop.schedules.entity.Schedule;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="comments")
public class Comment extends BaseEntity{

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;//댓글 아이디

    @Column
    private String nickname;//댓글 작성자명

    @Column(nullable=false)
    private String comment;//댓글 내용

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;//댓글이 달린 일정 객체(ID)

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
