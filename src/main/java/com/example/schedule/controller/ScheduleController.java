package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedules") // API URL 기본 경로 설정
public class ScheduleController {
  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  //일정 생성
  @PostMapping // 일정 생성 요청
  public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
    // ServiceLayer 호출 및 응답
    return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
  }

  // 일정 조회
  @GetMapping("/{id}")
  public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
    return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
  }
  // 전체 일정 조회
  @GetMapping
  public List<ScheduleResponseDto> findAllSchedules() {
    return scheduleService. findAllSchedules();
  }
  //일정 수정
  @PutMapping("/{id}")
  public ResponseEntity<ScheduleResponseDto> updateSchedule(
      @PathVariable Long id,
      @RequestBody ScheduleUpdateRequestDto requestDto) {

    return new ResponseEntity<>(
        scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getUserId(), requestDto.getPassword()),
        HttpStatus.OK
    );


  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestParam String password){
    scheduleService.deleteSchedule(id, password);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @GetMapping(params = "user")
  public List<ScheduleResponseDto> getSchedulesByUserId(@RequestParam("user") Long userId) {
    return scheduleService.findSchedulesByUserId(userId);
  }
}