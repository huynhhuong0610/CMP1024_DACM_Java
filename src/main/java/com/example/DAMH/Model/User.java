package com.example.DAMH.Model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username",nullable = false,length = 255)
    private String username;

    @Column(nullable = false,length = 255)
    private String email;

    @Column(nullable = false,length = 255)
    private String password;

    @Column(nullable = false,length = 255)
    private String address;

    @Column(nullable = false,length = 255)
    private String phonenumber;

    @Column(nullable = false,length = 255)
    private boolean enabled;

    @Column(nullable = true, length = 255)
    private String photourl;

    @Column(nullable = true, length = 255)
    private String tokenforgotpassword;

    @Column(nullable = true)
    private LocalDateTime timeexprired;

    @Transient
    public String getPhotosImagePath() {
        if(photourl == null || id == null)
            return null;
        return "/photos/" + id + "/" + photourl;
    }

    public void addRole (Role role) {
        this.roles.add(role);
    }

    public User(){
        roles = new HashSet<>();
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blogs;
}
