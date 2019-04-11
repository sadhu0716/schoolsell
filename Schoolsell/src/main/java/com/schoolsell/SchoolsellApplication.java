package com.schoolsell;

//import com.schoolsell.config.myWebSocket;
import com.schoolsell.config.MyWebSocket;
//import com.sun.glass.ui.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.schoolsell.dao")
@ComponentScan(basePackages = {"com.schoolsell.controller","com.schoolsell.service",
        "com.schoolsell.config", "com.schoolsell.entity","com.schoolsell.service.Impl","com.schoolsell.util"})
public class SchoolsellApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(SchoolsellApplication.class);
    }

    public static void main(String[] args) {
//        SpringApplication.run(SchoolsellApplication.class);
        ConfigurableApplicationContext applicationContext= SpringApplication.run(SchoolsellApplication.class, args);
        MyWebSocket.setApplicationContext(applicationContext);

    }
}
