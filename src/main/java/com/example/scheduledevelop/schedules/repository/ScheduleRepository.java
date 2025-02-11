package com.example.scheduledevelop.schedules.repository;

import com.example.scheduledevelop.schedules.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository  extends JpaRepository<Schedule, Long> {
}//일정 repository. 데이터 처리
