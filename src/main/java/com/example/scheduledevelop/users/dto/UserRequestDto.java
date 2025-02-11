package com.example.scheduledevelop.users.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {//유저 요청 객체

    private final String username;

    private final String email;

    public UserRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
