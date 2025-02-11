package com.example.scheduledevelop.users.service;

import com.example.scheduledevelop.users.dto.UserRequestDto;
import com.example.scheduledevelop.users.dto.UserResponseDto;
import com.example.scheduledevelop.users.entity.User;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {//유저service

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto signUp(UserRequestDto dto) {//저장
        User user= new User(dto.getUsername(), dto.getPassword(), dto.getEmail());
        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser.getId(),
                saveUser.getUsername(),
                saveUser.getEmail(),
                saveUser.getCreatedAt(),
                saveUser.getModifiedAt());
    }

    @Transactional(readOnly=true)
    public List<UserResponseDto> findAll() {//전체 목록 조회
        List<User> uList=userRepository.findAll();
        List<UserResponseDto> dtos=new ArrayList<>();

        for( User user : uList ){
            dtos.add(new UserResponseDto(user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt())
            );
        }
        return dtos;
    }

    @Transactional(readOnly=true)
    public UserResponseDto findById(Long id) {//특정 유저 조회
        User user= userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 유저는 없습니다."));

        return new UserResponseDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    @Transactional
    public UserResponseDto updateById(Long id, UserRequestDto dto) {//특정 유저 수정
        User user = userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 유저는 없습니다."));

        user.update(dto.getUsername(),dto.getEmail());

        return new UserResponseDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    @Transactional
    public void deleteById(Long id) {//특정 유저 삭제
        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 유저는 없습니다.");
        }
        userRepository.deleteById(id);
        }
    }

