package com.example.scheduledevelop.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message ="이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message= "비밀번호를 입력해주세요.")
    private final String password;

}
