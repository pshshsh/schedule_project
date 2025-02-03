package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
  ScheduleResponseDto saveSchedule(Schedule schedule);
  List<ScheduleResponseDto> findSchedulesByUserId(Long userId);
  List<ScheduleResponseDto> findAllSchedules();
  Optional<Schedule>  findScheduleById(Long id);
  int deleteSchedule(Long id, String password);
  int updateSchedule(Long id, String title, String password);

}
