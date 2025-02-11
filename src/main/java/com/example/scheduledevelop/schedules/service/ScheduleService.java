package com.example.scheduledevelop.schedules.service;

import com.example.scheduledevelop.schedules.dto.ScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.ScheduleResponseDto;
import com.example.scheduledevelop.schedules.entity.Schedule;
import com.example.scheduledevelop.schedules.repository.ScheduleRepository;
import com.example.scheduledevelop.users.entity.User;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {//일정 service. controller에게 요청 객체를 받아 repository에 넘긴 후 응답 객체를 돌려준다.

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto){//일정 저장
        User findUser = userRepository.findUserByIdOrElseThrow(dto.getUserId());

        Schedule schedule= new Schedule( dto.getTitle(), dto.getContents());
        schedule.setUser(findUser);
        Schedule saveSchedule= scheduleRepository.save(schedule);

        return new ScheduleResponseDto(saveSchedule.getId(),
                saveSchedule.getUser().getUsername(),
                saveSchedule.getTitle(),
                saveSchedule.getContents(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly=true)
    public List<ScheduleResponseDto> findAll(){//전체 목록 조회
        List<Schedule> sList= scheduleRepository.findAll();
        List<ScheduleResponseDto> dtos= new ArrayList<>();
        for( Schedule schedule : sList ){
            dtos.add(new ScheduleResponseDto(schedule.getId(),
                    schedule.getUser().getUsername(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            ));
        }
        return dtos;
    }

    @Transactional(readOnly=true)
    public ScheduleResponseDto findById(Long id) {//특정 일정 찾기
        Schedule schedule= scheduleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 일정을 찾을 수 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleResponseDto updateById(Long id, ScheduleRequestDto dto) {//특정 일정 조회
        Schedule schedule = scheduleRepository. findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 일정은 없습니다.")
        );
        schedule.update(dto.getTitle(), dto.getContents());
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteById(Long id) {//특정 일정 삭제
        if(!scheduleRepository.existsById(id)){
            throw new IllegalArgumentException("해당 일정이 없습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
