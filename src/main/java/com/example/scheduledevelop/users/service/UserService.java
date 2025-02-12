package com.example.scheduledevelop.users.service;

import com.example.scheduledevelop.users.config.PasswordEncoder;
import com.example.scheduledevelop.users.dto.LoginResponseDto;
import com.example.scheduledevelop.users.dto.UserRequestDto;
import com.example.scheduledevelop.users.dto.UserResponseDto;
import com.example.scheduledevelop.users.entity.User;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {//유저service

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto signUp(UserRequestDto dto) {//저장

        String passwordEncoder = pwdEncoder(dto.getPassword());//패스워드 암호화 먼저 하기

        User user = new User(dto.getUsername(), passwordEncoder, dto.getEmail());

        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser.getId(),
                saveUser.getUsername(),
                saveUser.getEmail(),
                saveUser.getCreatedAt(),
                saveUser.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {//전체 목록 조회
        List<User> uList = userRepository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();

        for (User user : uList) {
            dtos.add(new UserResponseDto(user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt())
            );
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {//특정 유저 조회
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 없습니다."));

        return new UserResponseDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    @Transactional
    public UserResponseDto updateById(Long id, UserRequestDto dto) {//특정 유저 수정
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 없습니다."));

        user.update(dto.getUsername(), dto.getEmail());

        return new UserResponseDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    @Transactional
    public void deleteById(Long id) {//특정 유저 삭제
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 유저는 없습니다.");
        }
        userRepository.deleteById(id);
    }

    public LoginResponseDto login(String email, String password) {//로그인

        User user = userRepository.findByEmail(email);//1.이메일로 유저 정보 찾기
        Optional<User> ou = Optional.ofNullable(user);//2.유저를 optional로 감싸기
        LoginResponseDto loginDto = null;

       if(!ou.isEmpty()&&pwdMatches(password, user.getPassword())) {//3.객체가 비지 않았고, 비밀번호가 DB랑 맞다면,
            loginDto= new LoginResponseDto(user.getId());//4.응답DTO 생성
        }

       return loginDto;
    }

    public String pwdEncoder(String rawPassword) {//패스워드 암호화 메소드

        PasswordEncoder pe = new PasswordEncoder();

        return pe.encode(rawPassword);

    }

    public boolean pwdMatches(String rawPassword, String encPassword) {//암호화된 패스워드와 입력 패스워드 비교

        PasswordEncoder pe = new PasswordEncoder();

        return pe.matches(rawPassword, encPassword);

    }

}

