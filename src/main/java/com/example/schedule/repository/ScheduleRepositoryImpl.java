package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
  private final Map<Long, Schedule> scheduleList = new HashMap<>();

  @Override
  public Schedule saveSchedule(Schedule schedule) {
    // memo 식별자 자동 생성
    Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
    schedule.setId(scheduleId);
    scheduleList.put(scheduleId, schedule);
    return schedule;
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules() {
    // init List
    List<ScheduleResponseDto> allSchedules = new ArrayList<>();
    // HashMap<Schedule> -> List<ScheduleResponseDto>
    for (Schedule schedule : scheduleList.values()) {
      ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
      allSchedules.add(responseDto);
    }
    return allSchedules;
  }
}
