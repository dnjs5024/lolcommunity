package com.example.demo.riot.schedule;

import java.sql.Date;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestSchedule {
	
	private ThreadPoolTaskScheduler scheduler;
	 
    public void stopScheduler() {
        scheduler.shutdown();
    }
 
    public void startScheduler() {
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        // 스케쥴러가 시작되는 부분 
        scheduler.schedule(getRunnable(), getTrigger());
    }
 
    private Runnable getRunnable(){
        return () -> {
            log.info("test2");
        };
    }
 
    private CronTrigger getTrigger() {
    	log.info("test1");
        return new CronTrigger("0/5 * * * * ?");
    }
	
}
