package ru.netology.springboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboot.profile.DevProfile;
import ru.netology.springboot.profile.ProductionProfile;
import ru.netology.springboot.profile.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean(name = "devProfile")
    @ConditionalOnExpression("${properties.netology.profile.dev:true}")
//    @ConditionalOnProperty(
//            value = "netology.profile.dev",
//            havingValue = "true",
//            matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean(name = "productionProfile")
    @ConditionalOnExpression("${properties.netology.profile.dev:false}")
//    @ConditionalOnProperty(
//            value = "netology.profile.dev",
//            havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
