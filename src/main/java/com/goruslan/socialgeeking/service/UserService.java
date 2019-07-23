package com.goruslan.socialgeeking.service;

import com.goruslan.socialgeeking.domain.User;
import com.goruslan.socialgeeking.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        encoder = new BCryptPasswordEncoder();
    }

    public User register(User user) {
        // Take the password from the form and encode
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);

        /* Confirm password
            - Setting user's pass to encrypted pass. When we save it, @PasswordMatch annotation will throw validation constraint error.
            - If validation constraint error exists, 'save' method won't work. */
        user.setConfirmPassword(secret);

        // Assigning a role to the user
        user.addRole(roleService.findByName("ROLE_USER"));

        // Set an activation code

        // Disable the user before saving.

        // Save the user.
        save(user);

        // Send activation email.
        sendActivationEmail(user);

        // Return user.

        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    @Transactional
    public void saveUsers(User... users) {
        for(User user : users) {
            logger.info("Saving User: " + user.getEmail());
            userRepository.save(user);
        }
    }

    public void sendActivationEmail(User user) {
        // Send the email

    }

}
