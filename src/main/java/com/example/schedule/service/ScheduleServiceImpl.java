package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

  @Override
  public List<ScheduleResponseDto> findAllSchedules() {
    // 전체 일정 조회
    List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

    return allSchedules;
  }

  @Override
  public ScheduleResponseDto findScheduleById(Long id) {
    Schedule schedule = scheduleRepository.findScheduleById(id);
    // NPE 방지
    if (schedule == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }

    return new ScheduleResponseDto(schedule);
  }

  @Override
  public ScheduleResponseDto updateSchedule(Long id, String title, Long userId, String password) {
    // 일정 조회
    Schedule schedule = scheduleRepository.findScheduleById(id);
    // NPE 방지
    if (schedule == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }
    // 필수값 검증
    if (title == null || userId == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
    }
    // Schedule 수정
    schedule.update(title, userId);

    return new ScheduleResponseDto(schedule);
  }
}
