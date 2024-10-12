package app.webssh;

import common.module.jpa.BaseJpaRepoFactoryBean;
import common.module.jpa.EnableAppJpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAppJpa(repositoryFactoryBeanClass = BaseJpaRepoFactoryBean.class)
public class WebSSHStarter {
    public static void main(String[] args) {
        SpringApplication.run(WebSSHStarter.class);
    }
}
