package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface  ScheduleService {
  ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);
  List<ScheduleResponseDto> findAllSchedules();
  List<ScheduleResponseDto> findSchedulesByUserId(Long userId);
  ScheduleResponseDto findScheduleById(Long id);
  ScheduleResponseDto updateSchedule(Long id, String title, Long userId, String password);
  void deleteSchedule(Long id, String password);
}
