package com.cron.scheduler.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@Component
public class SchedulerTask implements SchedulingConfigurer {

    @Autowired
    private ScheduleOperations scheduleOperations;

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        scheduleOperations.performTask();
                    }
                },
                new Trigger() {
                    @Override
                    public Instant nextExecution(TriggerContext context) {
                        Optional<Instant> lastCompletionTime = Optional.ofNullable(context.lastCompletion());
                        Instant nextExecutionTime = lastCompletionTime.orElseGet(Instant::now)
                                .plusMillis(scheduleOperations.getDelay());
                        return Instant.from(nextExecutionTime);
                    }
                }
        );
    }
}
