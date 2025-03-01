package com.example.scheduledevelop.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {//유저 요청 객체

    @Size(min=1, max=4, message="4자 이내로 입력해주세요.")
    private final String username;

    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message ="이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message= "비밀번호를 입력해주세요.")
    private final String password;

    public UserRequestDto(String username, String password, String email) {
        this.username = username;
        this.password= password;
        this.email = email;
    }
}
