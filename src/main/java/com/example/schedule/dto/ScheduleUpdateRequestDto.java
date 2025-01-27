package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
  private String title;
  private Long userId;
  private String password;
}
