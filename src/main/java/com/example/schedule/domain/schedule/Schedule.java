package com.example.schedule.domain.schedule;

import com.example.schedule.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Long writerId;

    public Schedule(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}