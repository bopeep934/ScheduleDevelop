package com.example.scheduledevelop.schedules.repository;

import com.example.scheduledevelop.comment.entity.Comment;
import com.example.scheduledevelop.schedules.entity.Schedule;
import com.example.scheduledevelop.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository  extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findScheduleById(Long Id);

    default Schedule findScheduleByIdOrElseThrow(Long Id) {
        return findScheduleById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exit ScheduleId " + Id));
    }
}//일정 repository. 데이터 처리
