package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
  private Long id; //일정 고유 ID
  private Long userId; // 작성자 ID
  private String title; //일정 제목
  private String description; // 일정 설명
  private LocalDateTime date; //날짜와 시간 저장 타입
  private LocalDateTime createdAt;  //일정 생성 시간
  private LocalDateTime updatedAt; // 일정 수정 시간
  private String password;//수정하고 삭제할 때 필요한 비밀번호

  public Schedule(Long userId, String title, String description, LocalDateTime date, String password){
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.date = date;
    this.password = password;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
