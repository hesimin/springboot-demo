package com.github.hesimin.springboot;

import com.github.hesimin.springboot.config.MyWebAppConfigurer;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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
@SpringBootApplication // 本类不能放在包外（这里如放到和包com同级），否则会出错。本类所在的包会被作为scan的默认根路径
@ServletComponentScan //Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
@MapperScan("com.github.hesimin.springboot.mapper")// mybatis mapper
public class SpringBootApp {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootApp.class);

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
        logger.info("========== 配置内置tomcat ==========");
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
//        tomcatFactory.setPort(8081);
        tomcatFactory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            //设置最大连接数
            protocol.setMaxConnections(500);
            //设置最大线程数
            protocol.setMaxThreads(800);
            protocol.setConnectionTimeout(8000);
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
                logger.info("========== doGet() ==========");
                doPost(req, resp);
            }

            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                logger.info("========== doPost() ==========");
                resp.getWriter().print("this servlet.");
            }
        }, "/servlet/test");
    }

    @Bean
    public ServletListenerRegistrationBean contextListener() {
        return new ServletListenerRegistrationBean<>(new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent servletContextEvent) {
                logger.info("-->ServletContext初始化:" + servletContextEvent.getServletContext().getServerInfo());
            }

            @Override
            public void contextDestroyed(ServletContextEvent servletContextEvent) {
                logger.info("-->ServletContext销毁");
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

    /**
     * 配置错误页面
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                System.out.println("config error page.");
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

                container.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
    }
}
