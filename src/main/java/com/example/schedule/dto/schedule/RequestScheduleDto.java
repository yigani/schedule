package com.example.schedule.dto.schedule;

import lombok.Getter;

@Getter
public class RequestScheduleDto {
    private String title;
    private String content;
    private Long writerId;
}