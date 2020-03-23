package com.mq.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mq.music.mapper")
@SpringBootApplication()
public class MymusicMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymusicMainApplication.class, args);
    }

}
