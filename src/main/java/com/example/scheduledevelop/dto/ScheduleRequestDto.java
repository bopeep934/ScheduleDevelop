package com.example.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private final String title;
    private final String username;
    private final String contents;

    public ScheduleRequestDto(String title, String username, String contents) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }
}
