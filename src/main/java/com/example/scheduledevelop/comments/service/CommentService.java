package com.example.scheduledevelop.comments.service;

import com.example.scheduledevelop.comments.dto.CommentRequestDto;
import com.example.scheduledevelop.comments.dto.CommentResponseDto;
import com.example.scheduledevelop.comments.entity.Comment;
import com.example.scheduledevelop.comments.repository.CommentRepository;
import com.example.scheduledevelop.schedules.entity.Schedule;
import com.example.scheduledevelop.schedules.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {//댓글 Service

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto save(CommentRequestDto dto){//댓글 저장

        Schedule schedule =scheduleRepository.findScheduleByIdOrElseThrow(dto.getScheduleId());
        Comment comment= new Comment(dto.getNickname(), dto.getComment(),schedule);
        Comment savedComment= commentRepository.save(comment);

        return new CommentResponseDto(savedComment.getId(),
                savedComment.getNickname(),
                savedComment.getComment(),
                savedComment.getSchedule().getTitle(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt());
    }

    @Transactional(readOnly=true)
    public List<CommentResponseDto> findAll() {//댓글 전체 목록 조회
        List<Comment> a= commentRepository.findAll();
        List<CommentResponseDto> cList= new ArrayList<>();
        for(Comment comment: a){
            cList.add(new CommentResponseDto(comment.getId(),
                    comment.getNickname(),
                    comment.getComment(),
                    comment.getSchedule().getTitle(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()));
        }
        return cList;
    }

    @Transactional(readOnly=true)
    public List<CommentResponseDto> findByScheduleId(Long scheduleId) {//일정별 댓글 목록 조회
        Schedule schedule =scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        List<Comment> a= commentRepository.findBySchedule(schedule);
        List<CommentResponseDto> cList=new ArrayList<>();
        for(Comment comment : a){
            cList.add(new CommentResponseDto(comment.getId(),
                    comment.getNickname(),
                    comment.getComment(),
                    comment.getSchedule().getTitle(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()));
        }
        return cList;
    }

    @Transactional(readOnly=true)
    public CommentResponseDto findById(Long id) {//특정 댓글 조회
        Comment comment =commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾는 댓글이 없습니다.")
        );
        return new CommentResponseDto(comment.getId(),
                comment.getNickname(),
                comment.getComment(),
                comment.getSchedule().getTitle(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    @Transactional
    public CommentResponseDto updateById(Long id, CommentRequestDto dto) {//특정 댓글 수정
        Comment comment =commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾는 댓글이 없습니다.")
        );
        comment.update(dto.getNickname(),dto.getComment());

        return new CommentResponseDto(comment.getId(),
                comment.getNickname(),
                comment.getComment(),
                comment.getSchedule().getTitle(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    @Transactional
    public void deleteById(Long id) {//특정 댓글 삭제
        if(!commentRepository.existsById(id))
            throw new IllegalArgumentException("찾는 댓글이 없습니다.");

        commentRepository.deleteById(id);
    }
}
