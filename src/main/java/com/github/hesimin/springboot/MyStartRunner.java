package com.github.hesimin.springboot;

import com.alibaba.fastjson.JSON;
import com.github.hesimin.springboot.config.GetAutoConfig;
import com.github.hesimin.springboot.config.GetConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在服务启动时执行
 * @author hesimin 2017-03-29
 */
@Component
@Order(100)// 顺序:自然排序值小优先
public class MyStartRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyStartRunner.class);

    @Autowired
    private GetConfig     getConfig;
    @Autowired
    private GetAutoConfig getAutoConfig;


    @Override
    public void run(String... strings) throws Exception {
        logger.info("服务启动执行:" + JSON.toJSONString(strings));

        logger.info("getConfig.getPath():"+getConfig.getPath());
        logger.info("getAutoConfig.getTest :"+getAutoConfig.getTest());
    }
}
