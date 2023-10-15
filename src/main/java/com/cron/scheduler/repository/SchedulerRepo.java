package com.cron.scheduler.repository;

import com.cron.scheduler.domain.ScheduleSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface SchedulerRepo extends JpaRepository<ScheduleSpec,Integer>{
}
