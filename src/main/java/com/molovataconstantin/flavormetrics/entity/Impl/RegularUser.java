package com.molovataconstantin.flavormetrics.entity.Impl;


import com.molovataconstantin.flavormetrics.entity.Email;
import com.molovataconstantin.flavormetrics.entity.Profile;
import com.molovataconstantin.flavormetrics.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "regular_user")
public class RegularUser extends User {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public RegularUser() {
        // No args constructor for JPA
    }

    public RegularUser(String password, Email username) {
        super(password, username);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}