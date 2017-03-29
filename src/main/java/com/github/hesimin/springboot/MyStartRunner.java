package com.github.hesimin.springboot;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author hesimin 2017-03-29
 */
@Component
@Order(100)
public class MyStartRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("---->服务启动执行:" + JSON.toJSONString(strings));
    }
}
