package com.github.hesimin.springboot.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hesimin 2017-03-23
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("")
    public Map record(HttpServletRequest request) {
        logger.info("接受参数：" + JSON.toJSONString(request.getParameterMap()));

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }
}
