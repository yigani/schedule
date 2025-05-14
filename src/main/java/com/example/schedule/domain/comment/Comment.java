package com.example.schedule.domain.comment;

import com.example.schedule.domain.schedule.Schedule;
import com.example.schedule.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Long writerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    public Comment(String content, Long writerId, Schedule schedule, Comment parentComment) {
        this.content = content;
        this.writerId = writerId;
        this.schedule = schedule;
        this.parentComment = parentComment;
    }

    public void update(String content) {
        this.content = content;
    }

    public boolean isReply() {
        return this.parentComment != null;
    }

}