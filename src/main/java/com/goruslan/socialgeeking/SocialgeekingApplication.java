package com.goruslan.socialgeeking;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SocialgeekingApplication {

    public static void main(String[] args) {

        SpringApplication.run(SocialgeekingApplication.class, args);

    }

    @Bean
    PrettyTime prettyTime() {
        return new PrettyTime();
    }


}