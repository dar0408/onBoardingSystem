package com.bridgelabz.onboarding.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="app_users")
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true) private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;

    // getters/setters
}
