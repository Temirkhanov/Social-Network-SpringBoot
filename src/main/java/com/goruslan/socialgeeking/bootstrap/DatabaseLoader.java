package com.goruslan.socialgeeking.bootstrap;

import com.goruslan.socialgeeking.domain.Comment;
import com.goruslan.socialgeeking.domain.Post;
import com.goruslan.socialgeeking.domain.Role;
import com.goruslan.socialgeeking.domain.User;
import com.goruslan.socialgeeking.repository.CommentRepository;
import com.goruslan.socialgeeking.repository.PostRepository;
import com.goruslan.socialgeeking.repository.RoleRepository;
import com.goruslan.socialgeeking.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    private Map<String,User> users = new HashMap<>();


    public DatabaseLoader(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        // add users and roles
        addUserAndRoles();
        Map<String,String> posts = new HashMap<>();
        posts.put("Securing Spring Boot APIs and SPAs with OAuth 2.0","https://auth0.com/blog/securing-spring-boot-apis-and-spas-with-oauth2/?utm_source=reddit&utm_medium=sc&utm_campaign=springboot_spa_securing");
        posts.put("Easy way to detect Device in Java Web Application using Spring Mobile - Source code to download from GitHub","https://www.opencodez.com/java/device-detection-using-spring-mobile.htm");
        posts.put("Tutorial series about building microservices with SpringBoot (with Netflix OSS)","https://medium.com/@marcus.eisele/implementing-a-microservice-architecture-with-spring-boot-intro-cdb6ad16806c");
        posts.put("Detailed steps to send encrypted email using Java / Spring Boot - Source code to download from GitHub","https://www.opencodez.com/java/send-encrypted-email-using-java.htm");
        posts.put("Build a Secure Progressive Web App With Spring Boot and React","https://dzone.com/articles/build-a-secure-progressive-web-app-with-spring-boo");
        posts.put("Building Your First Spring Boot Web Application - DZone Java","https://dzone.com/articles/building-your-first-spring-boot-web-application-ex");
        posts.put("Building Microservices with Spring Boot Fat (Uber) Jar","https://jelastic.com/blog/building-microservices-with-spring-boot-fat-uber-jar/");
        posts.put("Spring Cloud GCP 1.0 Released","https://cloud.google.com/blog/products/gcp/calling-java-developers-spring-cloud-gcp-1-0-is-now-generally-available");
        posts.put("Simplest way to Upload and Download Files in Java with Spring Boot - Code to download from Github","https://www.opencodez.com/uncategorized/file-upload-and-download-in-java-spring-boot.htm");
        posts.put("Add Social Login to Your Spring Boot 2.0 app","https://developer.okta.com/blog/2018/07/24/social-spring-boot");
        posts.put("File download example using Spring REST Controller","https://www.jeejava.com/file-download-example-using-spring-rest-controller/");

        posts.forEach((k,v) -> {
            User u1 = users.get("user@gmail.com");
            User u2 = users.get("super@gmail.com");
            Post post = new Post(k, v);
            if(k.startsWith("Build")) {
                post.setUser(u1);
            } else {
                post.setUser(u2);
            }


            postRepository.save(post);

            Comment spring = new Comment("Thank you for this link related to Spring Boot. I love it, great post!", post);
            Comment security = new Comment("I love that you're talking about Spring Security", post);
            Comment pwa = new Comment("What is this Progressive Web App thing all about? PWAs sound really cool.", post);
            Comment comments[] = {spring,security,pwa};
            for(Comment comment : comments) {
                commentRepository.save(comment);
                post.addComment(comment);
            }

        });

        long postCount = postRepository.count();
        System.out.println("Number of posts in the database: " + postCount );
    }


    private void addUserAndRoles() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User user = new User("user@gmail.com",secret,true,"John Doe","Bachelors in Computer Science","4 Years as a Software Engineer","React, Spring Boot, Java, Python",
                "JoDoe");
        user.addRole(userRole);
        user.setConfirmPassword(secret);
        userRepository.save(user);
        users.put("user@gmail.com", user);

        User admin = new User("admin@gmail.com",secret,true,"Admin Uiet","Master in CS","12 Years as a Web Developer","JavaScript, Angular, Java, Python, Ruby",
                "AdminUe");
        admin.addRole(adminRole);
        admin.setConfirmPassword(secret);
        userRepository.save(admin);

        User master = new User("super@gmail.com",secret,true,"Super Duper","PhD in Artificial Intel.","23 years as a Database Professional", "SQL, MangoDB, Spring MVC", "SuperrrR");
        master.addRoles(new HashSet<>(Arrays.asList(userRole,adminRole)));
        master.setConfirmPassword(secret);
        userRepository.save(master);

        users.put("user@gmail.com", user);
        users.put("super@gmail.com", master);

    }
}
