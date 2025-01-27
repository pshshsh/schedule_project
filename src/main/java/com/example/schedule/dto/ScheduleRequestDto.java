package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;
// 클라이언트가 일정 생성 요청 시 보낼 데이터
@Getter
public class ScheduleRequestDto {
  private Long userId;
  private String title;
  private LocalDateTime date;
  private String password;


}