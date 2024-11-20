package com.SCM.SCM.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String userid;
    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(unique = true , nullable = false)
    private String email;
    private String phoneNumber;
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    // Information and verification
    private boolean enabled=false;
    private boolean emailVerified;
    private boolean phoneVerfified;
    // Login Method
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    // Mapping
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    

    





    
   
}
