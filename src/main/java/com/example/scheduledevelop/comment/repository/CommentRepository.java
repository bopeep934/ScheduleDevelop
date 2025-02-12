package com.example.scheduledevelop.comment.repository;

import com.example.scheduledevelop.comment.entity.Comment;
import com.example.scheduledevelop.schedules.entity.Schedule;
import com.example.scheduledevelop.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findBySchedule(Schedule schedule);
}
