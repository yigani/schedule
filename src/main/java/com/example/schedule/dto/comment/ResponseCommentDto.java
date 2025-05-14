package com.example.schedule.dto.comment;

import com.example.schedule.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseCommentDto {
    private Long id;
    private String content;
    private Long writerId;
    private Long scheduleId;
    private Long parentCommentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResponseCommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writerId = comment.getWriterId();
        this.scheduleId = comment.getSchedule().getId();
        this.parentCommentId = comment.getParentComment() != null ? comment.getParentComment().getId() : null;
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}