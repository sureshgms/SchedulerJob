package com.cron.scheduler.application;

import com.cron.scheduler.repository.SchedulerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ScheduleOperations {

    @Autowired
    SchedulerRepo schedulerRepo;
    public int getDelay() {
        int delay = Integer.parseInt(schedulerRepo.getReferenceById(1).getIntervalInMinutes());
        return delay;

    }

    public void performTask() {
        System.out.println("Task Performing...");
    }
}
