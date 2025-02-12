package com.example.scheduledevelop.comment.service;

import com.example.scheduledevelop.comment.dto.CommentRequestDto;
import com.example.scheduledevelop.comment.dto.CommentResponseDto;
import com.example.scheduledevelop.comment.entity.Comment;
import com.example.scheduledevelop.comment.repository.CommentRepository;
import com.example.scheduledevelop.schedules.entity.Schedule;
import com.example.scheduledevelop.schedules.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto save(CommentRequestDto dto){

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
    public List<CommentResponseDto> findAll() {
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
    public List<CommentResponseDto> findByScheduleId(Long scheduleId) {
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
    public CommentResponseDto findById(Long id) {
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
    public CommentResponseDto updateById(Long id, CommentRequestDto dto) {
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
    public void deleteById(Long id) {
        if(!commentRepository.existsById(id))
            throw new IllegalArgumentException("찾는 댓글이 없습니다.");

        commentRepository.deleteById(id);
    }
}
