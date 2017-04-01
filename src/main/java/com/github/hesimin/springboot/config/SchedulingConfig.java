package com.github.hesimin.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author hesimin 2017-04-01
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0/60 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        logger.info(" ====> scheduled ... ");
    }
}
