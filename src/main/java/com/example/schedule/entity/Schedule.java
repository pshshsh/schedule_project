package com.example.schedule.entity;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
  @Setter
  private Long id; //일정 고유 ID
  private Long userId; // 작성자 ID
  private String title; // 할 일
  private LocalDateTime date; //날짜와 시간 저장 타입
  private LocalDateTime createdAt;  //일정을 작성한 시간
  private LocalDateTime updatedAt; // 일정을 수정한 시간
  private String password;//수정하고 삭제할 때 필요한 비밀번호

  //생성자
  public Schedule(Long userId, String title, LocalDateTime date, String password) {
    this.userId = userId;
    this.title = title;
    this.date = date;
    this.password = password;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  // 수정 메서드
  public void update(String title, Long userId) {
    this.title = title;
    this.userId = userId;
    this.updatedAt = LocalDateTime.now();
  }

}