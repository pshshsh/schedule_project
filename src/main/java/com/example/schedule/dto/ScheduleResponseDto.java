package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
// 서버에서 클라이언트에게 정보 반환
@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
  private Long id;
  private Long userId;
  private String title;
  private LocalDateTime date;
  private LocalDateTime createdAt; // 일정 생성 시간
  private LocalDateTime updatedAt; // 일정 수정 시간
 //클라이언트로 데이터를 반환하는 DTO
  public  ScheduleResponseDto(Schedule schedule){
    this.id = schedule.getId();
    this.userId = schedule.getUserId();
    this.title = schedule.getTitle();
    this.date = schedule.getDate();
    this.createdAt = schedule.getCreatedAt();
    this.updatedAt = schedule.getUpdatedAt();
  }

}