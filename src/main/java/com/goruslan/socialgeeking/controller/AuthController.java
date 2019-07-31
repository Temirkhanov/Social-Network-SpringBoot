package com.goruslan.socialgeeking.controller;

import com.goruslan.socialgeeking.domain.User;
import com.goruslan.socialgeeking.service.PostService;
import com.goruslan.socialgeeking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private PostService postService;
    private UserService userService;


    public AuthController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/{username}")
    public String profile(@PathVariable String username, Model model) {
        Optional<User> user = userService.findByUsername(username);
        if(user.isPresent()) {
            User currentUser = user.get();
            model.addAttribute("user", currentUser);
            model.addAttribute("posts", postService.findUserPosts(currentUser.getUsername()));
            return "auth/profile";
        }
        else {
            return "redirect:/ ";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            // Show validation errors.
            logger.info("Errors registering a new user.");
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "auth/register";
        } else {
            // Register new user.
            User newUser = userService.register(user);
            redirectAttributes
                    .addFlashAttribute("id", newUser.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/register";
        }
    }


}
