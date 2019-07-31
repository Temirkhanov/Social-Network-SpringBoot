package com.goruslan.socialgeeking.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;




@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @ManyToMany( mappedBy = "roles")
    private Collection<User> users;

}