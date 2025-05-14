package com.example.schedule.controller;

import com.example.schedule.dto.schedule.RequestScheduleDto;
import com.example.schedule.dto.schedule.ResponseScheduleDto;
import com.example.schedule.dto.schedule.ScheduleDetailDto;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseScheduleDto create(@RequestBody RequestScheduleDto request) {
        return scheduleService.create(request);
    }

    @GetMapping
    public List<ResponseScheduleDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseScheduleDto findById(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

    @GetMapping("/{id}/detail")
    public ScheduleDetailDto findDetailById(@PathVariable Long id) {
        return scheduleService.findDetailById(id);
    }

    @PutMapping("/{id}")
    public ResponseScheduleDto update(@PathVariable Long id, @RequestBody RequestScheduleDto request) {
        return scheduleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.delete(id);
    }
}