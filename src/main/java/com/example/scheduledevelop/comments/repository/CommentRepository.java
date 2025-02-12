package com.example.scheduledevelop.comments.repository;

import com.example.scheduledevelop.comments.entity.Comment;
import com.example.scheduledevelop.schedules.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//댓글 Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findBySchedule(Schedule schedule);

    Long countByScheduleId(Long scheduleId);
}
