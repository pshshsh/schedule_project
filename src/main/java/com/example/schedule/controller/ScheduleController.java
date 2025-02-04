package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/schedules") // API URL 기본 경로 설정
public class ScheduleController {
  private final ScheduleService scheduleService;
  // 생성자 주입 방식으로 SchuduleService 의존성 주입
  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  //일정 생성
  @PostMapping
  public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
    return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
  }

  // 단일 일정 조회
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
  // 일정 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestParam String password){
    scheduleService.deleteSchedule(id, password);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  // 사용자별 일정 조회
  @GetMapping(params = "user")
  public List<ScheduleResponseDto> getSchedulesByUserId(@RequestParam("user") Long userId) {
    return scheduleService.findSchedulesByUserId(userId);
  }
}