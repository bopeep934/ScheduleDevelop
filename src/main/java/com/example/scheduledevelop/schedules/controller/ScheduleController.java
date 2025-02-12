package com.example.scheduledevelop.schedules.controller;

import com.example.scheduledevelop.schedules.dto.PageResponseDto;
import com.example.scheduledevelop.schedules.dto.ScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.ScheduleResponseDto;
import com.example.scheduledevelop.schedules.entity.Schedule;
import com.example.scheduledevelop.schedules.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {//일정 controller. 일정정보를 받아 service로 넘김

    private final ScheduleService scheduleService;

    @PostMapping
    public Object save(
            @Valid @RequestBody ScheduleRequestDto dto,
            BindingResult bindingResult) {//새 일정 저장

        if (bindingResult.hasErrors()) {
         //   log.info("validation errors={}", bindingResult);
            // Field, Object Error 모두 JSON으로 반환
            return bindingResult.getAllErrors();
        }
        return ResponseEntity.ok(scheduleService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {//전체 목록 조회
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {//특정 일정 조회
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateById(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(scheduleService.updateById(id, dto));//특정 일정 수정
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {//특정 일정 삭제
        scheduleService.deleteById(id);
    }

    @GetMapping("/pageInfo")
    public ResponseEntity<Page<PageResponseDto>> pageInfo(@RequestParam (defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        Pageable pageable= PageRequest.of(page-1,size);
        return ResponseEntity.ok(scheduleService.getPage(pageable));
    }

}
