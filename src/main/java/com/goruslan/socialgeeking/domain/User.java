package com.goruslan.socialgeeking.domain;


import com.goruslan.socialgeeking.domain.validator.PasswordsMatch;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@PasswordsMatch
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Size(min = 8, max = 20)
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(length = 150)
    private String password;

    @NonNull
    @Column(nullable = true)
    private boolean enabled;

    // Sets up 3rd table between roles and users. User might have one or few roles so we fetch all the roles.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"), // (user) ID -> user_id
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id") // (role) id -> role_id
    )
    private Set<Role> roles = new HashSet<>();


    @NonNull
    @NotEmpty(message = "Full Name is required.")
    private String fullName;

    @NonNull
    @NotEmpty(message = "Education is required.")
    private String education;

    @NonNull
    @NotEmpty(message = "Experience is required.")
    private String experience;

    @NonNull
    @NotEmpty(message = "Summary of skills is required.")
    private String skills;

    @NonNull
    @NotEmpty(message = "Username is required.")
    @Column(nullable = false, unique = true)
    private String username;

    @Transient // This annotation is used to declare what instance variables cannot be persisted to database
    @NotEmpty(message = "Password Confirmation is required.")
    private String confirmPassword;



    public void addRole(Role role) {
        roles.add(role);
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
