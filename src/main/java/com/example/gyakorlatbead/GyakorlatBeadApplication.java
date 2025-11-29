package com.example.gyakorlatbead;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GyakorlatBeadApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GyakorlatBeadApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GyakorlatBeadApplication.class, args);
    }
}