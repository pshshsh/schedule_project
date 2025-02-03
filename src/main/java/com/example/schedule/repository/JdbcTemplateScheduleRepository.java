package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateScheduleRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public ScheduleResponseDto saveSchedule(Schedule schedule) {
    // INSERT Query를 직접 작성하지 않아도 된다.
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
    //파라미터 맵 생성
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("user_id", schedule.getUserId());
    parameters.put("title", schedule.getTitle());
    parameters.put("password", schedule.getPassword());
    parameters.put("date", schedule.getDate());
    parameters.put("created_at", LocalDateTime.now());
    // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    return new ScheduleResponseDto(
        key.longValue(),
        schedule.getUserId(),
        schedule.getTitle(),
        schedule.getDate(),
        LocalDateTime.now(),
        LocalDateTime.now());
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules() {
    return jdbcTemplate.query("SELECT * FROM schedule", scheduleRowMapper());
  }


  @Override
  public Optional<Schedule> findScheduleById(Long id) {
    List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapperV2(), id);
    return result.stream().findAny();
  }

  @Override
  public int deleteSchedule(Long id, String password) {
    return jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
  }


  @Override
  public int updateSchedule(Long id, String title, String password) {
    return jdbcTemplate.update(
        "UPDATE schedule SET title = ?, updated_at = NOW() WHERE id = ? AND password = ?",
        title, id, password
    );
  }

  private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
    return new RowMapper<ScheduleResponseDto>() {
      @Override
      public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ScheduleResponseDto(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getTimestamp("date") != null ? rs.getTimestamp("date").toLocalDateTime() : null,
            rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
            rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null

        );
      }
    };

  }


  private RowMapper<Schedule> scheduleRowMapperV2() {
    return new RowMapper<Schedule>() {
      @Override
        public Schedule mapRow (ResultSet rs,int rowNum) throws SQLException {
          return new Schedule(
              rs.getLong("id"),                        // ✅ ID 매핑 추가
              rs.getLong("user_id"),
              rs.getString("title"),
              rs.getTimestamp("date").toLocalDateTime(),
              rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
              rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
              rs.getString("password")

          );
        }

      };
  }
}
