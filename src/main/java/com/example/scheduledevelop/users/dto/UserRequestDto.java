package com.example.scheduledevelop.users.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {//유저 요청 객체

    private final String username;

    private final String email;

    private final String password;

    public UserRequestDto(String username, String password, String email) {
        this.username = username;
        this.password= password;
        this.email = email;
    }
}
