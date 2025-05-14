package com.example.schedule.controller;

import com.example.schedule.dto.comment.RequestCommentDto;
import com.example.schedule.dto.comment.ResponseCommentDto;
import com.example.schedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseCommentDto create(@RequestBody RequestCommentDto request) {
        return commentService.create(request);
    }

    // 특정 일정의 댓글 전체 조회
    @GetMapping("/schedule/{scheduleId}")
    public List<ResponseCommentDto> findBySchedule(@PathVariable Long scheduleId) {
        return commentService.findBySchedule(scheduleId);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseCommentDto update(@PathVariable Long id, @RequestBody RequestCommentDto request) {
        return commentService.update(id, request.getContent());
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }
}