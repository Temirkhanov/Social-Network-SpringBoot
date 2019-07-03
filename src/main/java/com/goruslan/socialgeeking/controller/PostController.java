package com.goruslan.socialgeeking.controller;

import com.goruslan.socialgeeking.domain.Post;
import com.goruslan.socialgeeking.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;


//@RestController
//@RequestMapping("/posts")

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "post/list";
    }

    @GetMapping("/post/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        if( post.isPresent() ) {
            model.addAttribute("post", post.get());
            model.addAttribute("success", model.containsAttribute("success"));
            return "post/view";

        } else {
            return "redirect:/ ";
        }
    }

    @GetMapping("/post/submit")
    public String newpostForm(Model model){
        model.addAttribute("post", new Post());
        return "post/submit";
    }

    @PostMapping("/post/submit")
    public String createpost(@Valid Post post, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors()){
            logger.info("Validation error while submitting a new post.");
            model.addAttribute("post", post);
            return "post/submit";
        } else {
            postRepository.save(post);
            logger.info("New Post was saved successfully.");
            redirectAttributes
                    .addAttribute("id", post.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/post/{id}";

        }
    }

}
