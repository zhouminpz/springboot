package com.zm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2017-12-19 14:32
 **/
@SpringBootApplication
@MapperScan("com.zm.mapper")
public class Application {
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }
}


