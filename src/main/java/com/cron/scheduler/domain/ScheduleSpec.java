package com.cron.scheduler.domain;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

@Entity
@Table(name="jobscheduler")
public class ScheduleSpec {

    @GeneratedValue
    @Id
    @Column(name="id")
    public Integer Id;

    @Column(name="interval_in_minutes")
    public String intervalInMinutes;

    public ScheduleSpec(Integer id, String intervalInMinutes) {
        Id = id;
        this.intervalInMinutes = intervalInMinutes;
    }

    public Integer getId() {
        return Id;
    }

    public String getIntervalInMinutes() {
        return intervalInMinutes;
    }

    public ScheduleSpec() {
    }
}
