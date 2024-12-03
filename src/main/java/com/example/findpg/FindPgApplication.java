package com.example.findpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:/mysql.properties"})
public class FindPgApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindPgApplication.class, args);
    }
}
