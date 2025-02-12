package com.example.scheduledevelop.comments.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {// 댓글 요청 DTO.

    private final Long scheduleId;//댓글을 달 일정 아이디

    private final String nickname;//댓글 작성자명

    private final String comment;//댓글 내용

    public CommentRequestDto(Long scheduleId, String nickname, String comment) {
        this.scheduleId = scheduleId;
        this.nickname = nickname;
        this.comment = comment;
    }
}
