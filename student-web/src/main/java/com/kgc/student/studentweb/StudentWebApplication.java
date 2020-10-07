package com.kgc.student.studentweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StudentWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentWebApplication.class, args);
    }

}
