package com.yuchai.itcommune;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
public class ItcommuneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItcommuneApplication.class, args);
    }
}
