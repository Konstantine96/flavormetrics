package com.molovataconstantin.flavormetrics.entity.Impl;


import com.molovataconstantin.flavormetrics.entity.Email;
import com.molovataconstantin.flavormetrics.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

    public Admin(String password, Email email) {
        super(password, email);
    }

    public Admin() {
        // No args constructor for JPA
    }
}
