package app.dateme;

import common.module.jpa.EnableJpa;
import common.module.webmvc.EnableWebMvc;
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
