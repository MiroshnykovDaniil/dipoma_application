package com.diploma.application;

import com.diploma.application.config.AppProperties;
//import com.diploma.application.config.JpaConfig;
import com.diploma.application.config.SecurityConfig;
import com.diploma.application.config.WebMvcConfig;
import com.diploma.application.filters.JwtRequestFilter;
import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
//@EnableJpaRepositories("com.diploma.application.repository")
//@Import(value = {SecurityConfig.class, WebMvcConfig.class})

public class DiplomaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DiplomaApplication.class);
    }
}
