package com.example.scheduledevelop.schedules.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PageResponseDto {
    private final Long id;//일정 아이디
    private final String title;//일정 제목
    private final String contents;//일정 내용
    private final Long commentIndex;//댓글 수(수정)
    private final String username;//유저 이름(수정)
    private final LocalDateTime createdAt;//작성일
    private final LocalDateTime modifiedAt;//수정일

    public PageResponseDto(Long id, String title, String contents, Long commentIndex, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.commentIndex = commentIndex;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
