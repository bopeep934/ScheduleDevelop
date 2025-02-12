package com.example.scheduledevelop.schedules.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {//일정 요청 객체

    @NotBlank(message="제목을 입력해주세요.")
    @Size(min=1,max=10, message="10자 내외로 입력해주세요.")
    private final String title;

    @NotBlank(message="사용자 아이디를 입력해주세요.")
    private final Long userId;

    @NotBlank(message="내용을 입력해주세요.")
    @Size(min=1,max=200, message="200자 내외로 입력해주세요.")
    private final String contents;

    public ScheduleRequestDto(String title, Long userId, String contents) {
        this.title = title;
        this.userId = userId;
        this.contents = contents;
    }
}
