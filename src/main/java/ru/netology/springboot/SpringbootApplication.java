package ru.netology.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.netology.springboot.profile.SystemProfile;

@SpringBootApplication(scanBasePackages = "ru.netology")
public class SpringbootApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class, args);

    }

}
