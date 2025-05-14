package com.example.schedule.dto.schedule;

import com.example.schedule.domain.schedule.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseScheduleDto {
    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int commentCount;  // ← 추가

    public ResponseScheduleDto(Schedule schedule, int commentCount) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writerId = schedule.getWriterId();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.commentCount = commentCount;
    }

    public ResponseScheduleDto(Schedule schedule) {
        this(schedule, 0);
    }
}