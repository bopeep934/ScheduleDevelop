package com.example.scheduledevelop.schedules.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {//일정 요청 객체

    private final String title;
    private final Long userId;
    private final String contents;

    public ScheduleRequestDto(String title, Long userId, String contents) {
        this.title = title;
        this.userId = userId;
        this.contents = contents;
    }
}
