package com.goruslan.socialgeeking.service;

import com.goruslan.socialgeeking.domain.Role;
import com.goruslan.socialgeeking.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

}
