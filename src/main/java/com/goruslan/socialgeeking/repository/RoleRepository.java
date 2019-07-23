package com.goruslan.socialgeeking.repository;

import com.goruslan.socialgeeking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
