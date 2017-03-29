package com.github.hesimin.springboot.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hesimin 2017-03-23
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${application.hello}")
    private String hello;

    @RequestMapping("/")
    public Map record(HttpServletRequest request) {
        //logger.info("接受参数：" + JSON.toJSONString(request.getParameterMap()));

        List<String> params = new ArrayList<>();

        Enumeration<String> keys=request.getParameterNames();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            String v = request.getParameter(key);
            params.add(key + "=" + v);
        }
        logger.info("---->"+JSON.toJSONString(params));

        if(System.currentTimeMillis()%3==1){

            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "随机返回失败");
            return result;
        }


        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/hello")
    public String hello(){
        return hello;
    }
}
