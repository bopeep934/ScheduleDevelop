package com.example.scheduledevelop.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private final Long scheduleId;

    private final String nickname;

    private final String comment;

    public CommentRequestDto(Long scheduleId, String nickname, String comment) {
        this.scheduleId = scheduleId;
        this.nickname = nickname;
        this.comment = comment;
    }
}
