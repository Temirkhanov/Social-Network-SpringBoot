package com.goruslan.socialgeeking.config;

import com.goruslan.socialgeeking.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            return Optional.of("master@gmail.com");
        }
        return Optional.of(
                ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
    }
}
