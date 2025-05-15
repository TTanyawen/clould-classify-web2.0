package com.example.java7_4.job;

import com.example.java7_4.service.impl.CalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatisticScheduledTasks {


    @Autowired
    private CalService calService;

    /**
     * 统计今天的数据，入统计表
     * 每天的20:50,21:50,22:50,23:50
     * */
    @Scheduled(cron = "0 50 20,21,22,23 * * ?")
    public void performSaveStatistics() {
        calService.calTodayData();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        log.info("testJob:{}", System.currentTimeMillis());
    }


}
