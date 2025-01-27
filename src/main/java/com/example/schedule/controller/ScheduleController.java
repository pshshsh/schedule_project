package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedules") // API URL 기본 경로 설정
public class ScheduleController {
  private final Map<Long, Schedule> scheduleList = new HashMap<>();

  //일정 생성
  @PostMapping
  public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
    // ID 자동 증가 로직
    Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

    // 요청 데이터로 Schedule 객체 생성
    Schedule schedule = new Schedule(scheduleId, requestDto.getUserId(), requestDto.getTitle(), requestDto.getDate(), requestDto.getPassword());

    // 실제 DB 저장 (현재는 메모리)
    scheduleList.put(scheduleId, schedule);

    return new ScheduleResponseDto(schedule);
  }

  // 일정 조회
  @GetMapping("/{id}")
  public ScheduleResponseDto findMemoById(@PathVariable Long id) {
    Schedule schedule = scheduleList.get(id);
    if (schedule == null) {
      throw new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다.");
    }

    return new ScheduleResponseDto(schedule);
  }

  // init List
  @GetMapping
  public List<ScheduleResponseDto> findAllSchedules() {
    List<ScheduleResponseDto> responseList = new ArrayList<>();
    // HashMap<Memo> -> List<MemoResponseDto>
    for (Schedule schedule : scheduleList.values()) {
      ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
      responseList.add(responseDto);
    }
    return responseList;
  }
  //일정 수정
  @PutMapping("/{id}")
  public ScheduleResponseDto updateScheduleById(
      @PathVariable Long id,
      @RequestBody ScheduleUpdateRequestDto requestDto) {

    //  데이터 조회
    Schedule schedule = scheduleList.get(id);
    if (schedule == null) {
      throw new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다.");
    }
    schedule.update(requestDto); // 스케줄 업데이트
    return new ScheduleResponseDto(schedule); //DTO로 변환후 반환
}
}