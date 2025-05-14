package com.example.schedule.dto.comment;

import lombok.Getter;

@Getter
public class RequestCommentDto {
    private String content;
    private Long writerId;
    private Long scheduleId;
    private Long parentCommentId;
}