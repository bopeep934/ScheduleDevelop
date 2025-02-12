package com.example.scheduledevelop.comments.controller;

import com.example.scheduledevelop.comments.dto.CommentRequestDto;
import com.example.scheduledevelop.comments.dto.CommentResponseDto;
import com.example.scheduledevelop.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {//댓글 controller

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@RequestBody CommentRequestDto dto){//댓글 저장
        return ResponseEntity.ok(commentService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll(){//댓글 전체 목록 조회
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/scheduleId/{id}")
    public ResponseEntity<List<CommentResponseDto>> findByScheduleId(@PathVariable Long id){//일정별 댓글 목록 조회
        return ResponseEntity.ok(commentService.findByScheduleId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> fineById(@PathVariable Long id){//특정 댓글 조회
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateById(@PathVariable Long id,
                                                         @RequestBody CommentRequestDto dto){//특정 댓글 수정
        return ResponseEntity.ok(commentService.updateById(id,dto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){//특정 댓글 삭제
        commentService.deleteById(id);
    }
}
