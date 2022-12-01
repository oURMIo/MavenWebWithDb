package com.example.demo;

import com.example.demo.web.ForFiles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    CommandLineRunner init(ForFiles forFiles) {
        return (args) -> {
            forFiles.deleteAll();
            forFiles.init();
        };
    }

}
