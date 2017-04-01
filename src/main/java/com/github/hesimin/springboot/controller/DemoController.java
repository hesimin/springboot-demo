package com.github.hesimin.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hesimin 2017-04-01
 */
@RestController
public class DemoController {
    @RequestMapping(value = "/demo/1", method = RequestMethod.GET)
    public String demo1(HttpServletRequest request,String arg) {
        return "demo1:" + arg;
    }

    @RequestMapping(value = "/demo/2", method = RequestMethod.POST)
    public String demo2(String arg) {
        return "demo2:" + arg;
    }

    @RequestMapping(value = "/api/test",method = RequestMethod.GET)
    public String api(String arg) {
        return "api:" + arg;
    }
}
