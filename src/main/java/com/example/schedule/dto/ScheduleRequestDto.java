package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;
// 클라이언트가 일정 생성 또는 수정 요청 시 보낼 데이터
@Getter
public class ScheduleRequestDto {
  private Long userId; // 작성자 ID
  private String title; // 할 일
  private LocalDateTime date; // 일정 날짜 및 시간
  private String password;


}