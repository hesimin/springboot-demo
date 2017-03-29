package com.github.hesimin.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author hesimin 2017-03-29
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 自定义静态资源映射目录（可在配置文件中配置）
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/myresource/**").addResourceLocations("classpath:/myresource/");

//         # 默认值为 /**  （只可定义一个？）
//         spring.mvc.static-path-pattern=
//         # 默认值为 classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
//         spring.resources.static-locations=这里设置要指向的路径，多个使用英文逗号隔开，
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
                System.out.println("========= 自定义拦截器：(Controller方法调用之前） =========");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
                System.out.println("========= 自定义拦截器：Controller方法调用之后，但是在视图被渲染之前 =========");
            }

            @Override
            public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
                System.out.println("========= 自定义拦截器：请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作） =========");
            }
        }).addPathPatterns("/**");

        registry.addWebRequestInterceptor(new WebRequestInterceptor() {
            @Override
            public void preHandle(WebRequest webRequest) throws Exception {

            }

            @Override
            public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {

            }

            @Override
            public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

            }
        }).addPathPatterns("/**");;
    }
}
