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

    @GetMapping("/session-home")
    public ResponseEntity<String> home(
            HttpServletRequest request
            //          Model model
    ) {
        System.out.println("세션 테스트");
        HttpSession session = request.getSession(false);

        if (session == null) {
            return ResponseEntity.ok("login");
        }

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        if (loginUser == null) {
            return ResponseEntity.ok("login");
        }

        String result = loginUser.getUsername() + "님 환영합니다.";

        //       model.addAttribute("loginUser", loginUser);

        return ResponseEntity.ok(result);
    }
}
