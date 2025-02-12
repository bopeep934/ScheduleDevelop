package com.example.scheduledevelop.comments.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {//댓글 응답 DTO.

    private final Long id;//댓글 고유 아이디

    private final String nickname;//댓글 작성자명

    private final String comment;//댓글 내용

    private final String scheduleTitle;//댓글을 단 일정 제목

    private final LocalDateTime createdAt;//댓글 작성일

    private final LocalDateTime modifiedAt;//댓글 수정일

    public CommentResponseDto(Long id, String nickname, String comment, String scheduleTitle, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.nickname = nickname;
        this.comment = comment;
        this.scheduleTitle = scheduleTitle;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
