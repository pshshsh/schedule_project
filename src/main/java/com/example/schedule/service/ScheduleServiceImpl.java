package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService{
  private final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
    // 요청 받은 데이터로 Memo 객체 생성 ID 없음
    Schedule schedule =  new Schedule(requestDto.getUserId(), requestDto.getTitle(), requestDto.getDate(), requestDto.getPassword());
    // DB저장
   Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

    return new ScheduleResponseDto(savedSchedule);
  }
}
