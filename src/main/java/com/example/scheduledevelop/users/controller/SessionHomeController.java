package com.example.scheduledevelop.users.controller;

import ch.qos.logback.core.model.Model;
import com.example.scheduledevelop.users.Const;
import com.example.scheduledevelop.users.dto.UserResponseDto;
import com.example.scheduledevelop.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SessionHomeController {

    private final UserService userService;

    @GetMapping("/session-home")//로그인 성공여부 확인 클래스
    public ResponseEntity<String> home(
            HttpServletRequest request
    ) {
        System.out.println("세션 테스트");

        HttpSession session = request.getSession(false);

        if (session == null) {
            return ResponseEntity.ok("로그인 하세요");
        }

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        if (loginUser == null) {
            return ResponseEntity.ok("로그인 하세요");
        }

        String result = loginUser.getUsername() + "님 환영합니다.";

        return ResponseEntity.ok(result);
    }
}
