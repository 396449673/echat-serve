package com.example.echatserve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.echatserve.Mapper")
public class EchatServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchatServeApplication.class, args);
    }

}
