package app.dateme;

import common.module.jpa.EnableAppJpa;
import common.module.jpa.JpaRepositoryImpl;
import common.module.webmvc.EnableWebMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAppJpa(repositoryBaseClass = JpaRepositoryImpl.class)
@EnableWebMvc
public class DatemeApp {

    public static void main(String[] args) {
        SpringApplication.run(DatemeApp.class);
    }

}
