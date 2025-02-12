package com.example.scheduledevelop.users.controller;

import jakarta.validation.Valid;
import com.example.scheduledevelop.users.Const;
import com.example.scheduledevelop.users.dto.LoginRequestDto;
import com.example.scheduledevelop.users.dto.LoginResponseDto;
import com.example.scheduledevelop.users.dto.UserResponseDto;
import com.example.scheduledevelop.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SessionUserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody
                                        LoginRequestDto dto,
                                        HttpServletRequest request) {

        LoginResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());

        Optional<LoginResponseDto> value = Optional.ofNullable(responseDto);

        if (!value.isPresent()) {//null값이면 로그인 실패 메세지를 띄우기
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("로그인에 실패했습니다.");
        } else {//로그인 성공

            Long userId = responseDto.getUserId();

            HttpSession session = request.getSession();//세션 생성

            //세션값 메소드 따로 생성:setAttribte();

            UserResponseDto loginUser = userService.findById(userId);

            session.setAttribute(Const.LOGIN_USER, loginUser);//세션에 로그인 유저 정보를 저장.

            return ResponseEntity.status(HttpStatus.CREATED).body("로그인에 성공했습니다.");

        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {//로그아웃

        HttpSession session = request.getSession(false);

        if (session != null) {//세션이 있다면 종료하기
            session.invalidate();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("로그아웃 했습니다.");
    }
}
