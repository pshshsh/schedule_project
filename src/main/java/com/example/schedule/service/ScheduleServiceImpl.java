package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
  private final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
    // 요청 받은 데이터로 Memo 객체 생성 ID 없음
    Schedule schedule = new Schedule(requestDto.getUserId(), requestDto.getTitle(), requestDto.getDate(), requestDto.getPassword());
    // DB저장
    return scheduleRepository.saveSchedule(schedule);
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules() {
    // 전체 일정 조회
    List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

    return allSchedules;
  }

  @Override
  public ScheduleResponseDto findScheduleById(Long id) {
    Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);
    // NPE 방지
    if (optionalSchedule.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }

    return new ScheduleResponseDto(optionalSchedule.get());
  }

  @Override
  public ScheduleResponseDto updateSchedule(Long id, String title, Long userId, String password) {

    // NPE 방지
    if (title == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is required.");
    }

    // schedule 수정
    int updatedRow = scheduleRepository.updateSchedule(id, title, password);
    // 수정된 row가 0개라면
    if (updatedRow == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been updated. Please check ID and password.");
    }
    Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);
    // 수정된 일정 다시 조회
    return new ScheduleResponseDto(optionalSchedule.get());
  }


  @Override
  public void deleteSchedule(Long id, String password) {
// memo 삭제
    int deletedRow = scheduleRepository.deleteSchedule(id, password);
    // 삭제된 row가 0개 라면
    if (deletedRow == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }


  }
}