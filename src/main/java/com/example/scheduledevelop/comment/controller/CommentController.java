package com.example.scheduledevelop.comment.controller;

import com.example.scheduledevelop.comment.dto.CommentRequestDto;
import com.example.scheduledevelop.comment.dto.CommentResponseDto;
import com.example.scheduledevelop.comment.service.CommentService;
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
    public ResponseEntity<CommentResponseDto> save(@RequestBody CommentRequestDto dto){
        return ResponseEntity.ok(commentService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/scheduleId/{id}")
    public ResponseEntity<List<CommentResponseDto>> findByScheduleId(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findByScheduleId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> fineById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateById(@PathVariable Long id,
                                                         @RequestBody CommentRequestDto dto){
        return ResponseEntity.ok(commentService.updateById(id,dto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        commentService.deleteById(id);
    }
}
