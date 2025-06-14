package com.molovataconstantin.flavormetrics.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    public Ingredient() {
        // Explicit no args constructor for JPA
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingredient that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}