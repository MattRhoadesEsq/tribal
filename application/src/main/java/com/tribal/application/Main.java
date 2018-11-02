package com.tribal.application;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application
 *
 */
@SpringBootApplication
public class Main
{
    public static void main(String[] args) {
        Logging.initialize();

        SpringApplication.run(Main.class, args);
    }

}
