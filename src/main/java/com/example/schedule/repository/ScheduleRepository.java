package com.example.schedule.repository;

import com.example.schedule.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}