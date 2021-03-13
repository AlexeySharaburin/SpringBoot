package ru.netology.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.netology.springboot.controller.ProfileController;
import ru.netology.springboot.profile.SystemProfile;

@SpringBootApplication(scanBasePackages = "ru.netology")
public class SpringbootApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(SpringbootApplication.class, args);

        var bean = context.getBean(ProfileController.class);
        var newBean = context.getBean("profileController");

        System.out.println(bean.toString());
        System.out.println(newBean.toString());

    }

}
