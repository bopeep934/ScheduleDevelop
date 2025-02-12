package com.example.scheduledevelop.users.controller;

import com.example.scheduledevelop.users.dto.UserRequestDto;
import com.example.scheduledevelop.users.dto.UserResponseDto;
import com.example.scheduledevelop.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {//유저 controller. 사용자로부터 정보를 받아 service로 보낸다.

    private final UserService userService;

    @PostMapping("/signup")
    public Object signUp(@Valid @RequestBody UserRequestDto dto,
    BindingResult bindingResult){//저장
        if (bindingResult.hasErrors()) {
            //   log.info("validation errors={}", bindingResult);
            // Field, Object Error 모두 JSON으로 반환
            return bindingResult.getAllErrors();
        }
        return ResponseEntity.ok(userService.signUp(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){//전체 목록 조회
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){//특정 유저 조회
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateById(@PathVariable Long id, @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(userService.updateById(id, dto));//특정 유저 수정
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){//특정 유저 삭제
         userService.deleteById(id);
    }
}
