package com.softuni.springintroex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@Configuration
@EnableJpaRepositories(basePackages = {
        "com.softuni.springintroex.repositories"
})*/

@SpringBootApplication
public class SpringIntroExApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntroExApplication.class, args);
    }

}
