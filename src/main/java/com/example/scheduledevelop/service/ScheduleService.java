package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.ScheduleRequestDto;
import com.example.scheduledevelop.dto.ScheduleResponseDto;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto){
        Schedule schedule= new Schedule(dto.getUsername(), dto.getTitle(), dto.getContents());
        Schedule saveSchedule= scheduleRepository.save(schedule);

        return new ScheduleResponseDto(saveSchedule.getId(),
                saveSchedule.getUsername(),
                saveSchedule.getTitle(),
                saveSchedule.getContents(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly=true)
    public List<ScheduleResponseDto> findAll(){
        List<Schedule> sList= scheduleRepository.findAll();
        List<ScheduleResponseDto> dtos= new ArrayList<>();
        for( Schedule schedule : sList ){
            dtos.add(new ScheduleResponseDto(schedule.getId(),
                    schedule.getUsername(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            ));
        }
        return dtos;
    }

    @Transactional(readOnly=true)
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule= scheduleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 일정을 찾을 수 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleResponseDto updateById(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository. findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 일정은 없습니다.")
        );
        schedule.update(dto.getTitle(), dto.getContents());
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteById(Long id) {
        if(!scheduleRepository.existsById(id)){
            throw new IllegalArgumentException("해당 일정이 없습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
