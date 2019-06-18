package com.goruslan.socialgeeking;

import com.goruslan.socialgeeking.domain.Comment;
import com.goruslan.socialgeeking.domain.Link;
import com.goruslan.socialgeeking.repository.CommentRepository;
import com.goruslan.socialgeeking.repository.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

@SpringBootApplication
@EnableJpaAuditing
public class SocialgeekingApplication {

    public static void main(String[] args) {

        SpringApplication.run(SocialgeekingApplication.class, args);


    }

    // @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
        return args -> {
            Link link = new Link("MyLink number1", "url.com/haha");
            linkRepository.save(link);
            Comment comment = new Comment("Comment from the main", link);
            commentRepository.save(comment);
            link.addComment(comment);
            System.out.println("INSERTED _____________+++++++++++++++______________");
        };
    }
}