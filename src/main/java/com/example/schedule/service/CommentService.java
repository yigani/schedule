package com.example.schedule.service;

import com.example.schedule.domain.comment.Comment;
import com.example.schedule.domain.schedule.Schedule;
import com.example.schedule.dto.comment.RequestCommentDto;
import com.example.schedule.dto.comment.ResponseCommentDto;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public ResponseCommentDto create(RequestCommentDto request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        Comment parent = null;
        if (request.getParentCommentId() != null) {
            parent = commentRepository.findById(request.getParentCommentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 없습니다."));

            if (parent.getParentComment() != null) {
                throw new IllegalArgumentException("대댓글에는 대댓글을 달 수 없습니다.");
            }
        }

        Comment comment = new Comment(request.getContent(), request.getWriterId(), schedule, parent);
        return new ResponseCommentDto(commentRepository.save(comment));
    }

    public List<ResponseCommentDto> findBySchedule(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId).stream()
                .sorted(Comparator.comparing((Comment c) ->
                                c.getParentComment() == null ? c.getId() : c.getParentComment().getId()) // 부모 기준 정렬
                        .thenComparing(Comment::getId)) // 같은 부모끼리 순서 정렬
                .map(ResponseCommentDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseCommentDto update(Long id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        comment.update(content);
        return new ResponseCommentDto(comment);
    }

    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        commentRepository.delete(comment);
    }
}