package com.example.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron="0/6 * * * * ?")
    public void reportCurrentTime() {
       /* System.out.println("现在时间：" + dateFormat.format(new Date()));*/
    }
}
