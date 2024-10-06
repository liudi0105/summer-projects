package app.dateme;

import common.module.jpa.BaseJpaRepoFactoryBean;
import common.module.jpa.EnableAppJpa;
import common.module.webmvc.EnableWebMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAppJpa(repositoryFactoryBeanClass = BaseJpaRepoFactoryBean.class)
@EnableWebMvc
public class DatemeApp {

    public static void main(String[] args) {
        SpringApplication.run(DatemeApp.class);
    }

}
