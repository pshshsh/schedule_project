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
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public  ScheduleResponseDto(Schedule schedule){
    this.id = schedule.getId();
    this.userId = schedule.getUserId();
    this.title = schedule.getTitle();
    this.date = schedule.getDate();
    this.createdAt = schedule.getCreatedAt();
    this.updatedAt = schedule.getUpdatedAt();
  }

}