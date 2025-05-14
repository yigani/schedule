package com.example.schedule.service;

import com.example.schedule.domain.schedule.Schedule;
import com.example.schedule.dto.comment.ResponseCommentDto;
import com.example.schedule.dto.schedule.RequestScheduleDto;
import com.example.schedule.dto.schedule.ResponseScheduleDto;
import com.example.schedule.dto.schedule.ScheduleDetailDto;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public ResponseScheduleDto create(RequestScheduleDto request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), request.getWriterId());
        Schedule saved = scheduleRepository.save(schedule);
        return new ResponseScheduleDto(saved);
    }

    public List<ResponseScheduleDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> {
                    int count = commentRepository.findByScheduleId(schedule.getId()).size();
                    return new ResponseScheduleDto(schedule, count);
                })
                .collect(Collectors.toList());
    }

    public ResponseScheduleDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id=" + id));
        return new ResponseScheduleDto(schedule);
    }

    public ResponseScheduleDto update(Long id, RequestScheduleDto request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id=" + id));
        schedule.update(request.getTitle(), request.getContent());
        return new ResponseScheduleDto(schedule);
    }

    public ScheduleDetailDto findDetailById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        List<ResponseCommentDto> comments = commentRepository.findByScheduleId(id).stream()
                .map(ResponseCommentDto::new)
                .collect(Collectors.toList());

        return new ScheduleDetailDto(schedule, comments);
    }

    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id=" + id));
        scheduleRepository.delete(schedule);
    }
}