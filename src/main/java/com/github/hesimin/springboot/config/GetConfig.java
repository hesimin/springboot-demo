package com.github.hesimin.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 获取配置、环境变量
 *
 * @author hesimin 2017-04-01
 */
@Configuration
public class GetConfig implements EnvironmentAware {// 被spring管理的类都可以通过 EnvironmentAware 接口得到Environment
    private static final Logger logger = LoggerFactory.getLogger(GetConfig.class);

    @Value("${logging.path}")
    private String path;

    @Override
    public void setEnvironment(Environment env) {
        logger.info("EnvironmentAware.setEnvironment");

        logger.info(env.getProperty("JAVA_HOME"));

        logger.info(path);
        logger.info(env.getProperty("logging.path"));

        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "logging.");
        logger.info(propertyResolver.getProperty("path"));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

