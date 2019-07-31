package com.goruslan.socialgeeking.service;


import com.goruslan.socialgeeking.domain.Post;
import com.goruslan.socialgeeking.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
//        return postRepository.findAll();
        return postRepository.findAllByOrderByLastModifiedDateDesc();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findUserPosts(String username) {
        return postRepository.findAllByCreatedBy(username);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

}
