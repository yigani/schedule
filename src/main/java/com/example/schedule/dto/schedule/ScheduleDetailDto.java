package com.example.schedule.dto.schedule;

import com.example.schedule.domain.schedule.Schedule;
import com.example.schedule.dto.comment.ResponseCommentDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleDetailDto {
    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ResponseCommentDto> comments;

    public ScheduleDetailDto(Schedule schedule, List<ResponseCommentDto> comments) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writerId = schedule.getWriterId();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.comments = comments;
    }
}