package com.example.scheduledevelop.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final String nickname;

    private final String comment;

    private final String scheduleTitle;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Long id, String nickname, String comment, String scheduleTitle, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.nickname = nickname;
        this.comment = comment;
        this.scheduleTitle = scheduleTitle;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
