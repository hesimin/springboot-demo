package com.github.hesimin.springboot;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * springBoot入口
 * <p>静态资源默认配置：目录，优先级顺序为：META/resources > resources > static > public </p>
 * @author hesimin 2017-03-23
 */
@SpringBootApplication
//@ServletComponentScan //Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
public class SpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    /**
     * 配置内置tomcat
     * <p>配置文件中可配：server.port、server.tomcat.max-threads</p>
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
        System.out.println("========== 配置内置tomcat ==========");
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
//        tomcatFactory.setPort(8081);
        tomcatFactory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            //设置最大连接数
            protocol.setMaxConnections(200);
            //设置最大线程数
            protocol.setMaxThreads(500);
            protocol.setConnectionTimeout(5000);
        });
        return tomcatFactory;
    }

    /**
     * 注册servlet
     * <p>同理可注册：FilterRegistrationBean、ServletListenerRegistrationBean</p>
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean testServlet() {
        return new ServletRegistrationBean(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                System.out.println("========== doGet() ==========");
                doPost(req, resp);
            }

            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                System.out.println("========== doPost() ==========");
                resp.getWriter().print("this servlet.");
            }
        }, "/servlet/test");
    }

    @Bean
    public ServletListenerRegistrationBean contextListener() {
        return new ServletListenerRegistrationBean<>(new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent servletContextEvent) {
                System.out.println("-->ServletContext初始化:" + servletContextEvent.getServletContext().getServerInfo());
            }

            @Override
            public void contextDestroyed(ServletContextEvent servletContextEvent) {
                System.out.println("-->ServletContext销毁");
            }
        });
    }

//    /**
//     * DispatcherServlet 默认配置修改
//     * @param dispatcherServlet
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.getUrlMappings().clear();
//        registration.addUrlMappings("*.do");
//        registration.addUrlMappings("*.json");
//        return registration;
//    }
}
