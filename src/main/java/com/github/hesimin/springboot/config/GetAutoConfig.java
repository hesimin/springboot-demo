package com.github.hesimin.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 通过spring自动配置properties bean
 * @author hesimin 2017-04-01
 */
@Configuration
//@ConditionalOnClass(LogProperties.class)//仅仅在一定条件下才会被加载，这里的条件是.class位于类路径上
@EnableConfigurationProperties(LogProperties.class)
public class GetAutoConfig {
    @Autowired
    private LogProperties properties;

    public String getTest(){
        return properties.getPath();
    }
}

@ConfigurationProperties(prefix = "logging")
class LogProperties {
    private String path;
    private String file;
    private String level;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
