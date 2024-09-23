package com.github.liudi0105.dateme;

import com.github.liudi0105.wrapper.spring.jpa.EnableJpa;
import com.github.liudi0105.wrapper.spring.webmvc.EnableWebMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpa
@EnableWebMvc
public class DatemeApp {

    public static void main(String[] args) {
        SpringApplication.run(DatemeApp.class);
    }

}
