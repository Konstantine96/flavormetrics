package com.molovataconstantin.flavormetrics.entity;



import com.molovataconstantin.flavormetrics.entity.Impl.Nutritionist;
import com.molovataconstantin.flavormetrics.exception.Impl.InvalidArgumentException;
import com.molovataconstantin.flavormetrics.model.enums.DietaryPreferenceType;
import com.molovataconstantin.flavormetrics.model.enums.DifficultyType;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "receipe")
public class Recipe implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "nutritionist_id")
    private Nutritionist nutritionist;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "prep_time_minutes")
    private Integer prepTimeMinutes;

    @Column(name = "cook_time_minutes")
    private Integer cookTimeMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private DifficultyType difficulty;

    @Column(name = "estimated_calories")
    private Integer estimatedCalories;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "receipe_tag",
            joinColumns = {
                    @JoinColumn(name = "receipe_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id")
            }
    )
    @Column(name = "tags")
    private List<Tag> tags;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "receipe_ingredient",
            joinColumns = {
                    @JoinColumn(name = "receipe_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ingredient_id")
            }
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Allergy> allergies = new ArrayList<>();
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "recipe_dietary_preferences",
            joinColumns = @JoinColumn(name = "recipe_id")
    )
    @Column(name = "dietary_preference")
    private List<DietaryPreferenceType> dietaryPreferences = new ArrayList<>();

    public Recipe() {
        // No args constructor for JPA
    }

    public Recipe(String name, List<Ingredient> ingredients) {
        if (name == null || name.isBlank()) {
            throw new InvalidArgumentException(
                    "Invalid name",
                    "name must be non-null and non-blank",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "recipe.name"
            );
        }

        if (ingredients == null || ingredients.isEmpty()) {
            throw new InvalidArgumentException(
                    "Invalid ingredients",
                    "ingredients must be non-null and non-empty",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "recipe.ingredients"
            );
        }

        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutritionist getNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(Nutritionist nutritionist) {
        this.nutritionist = nutritionist;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public void setPrepTimeMinutes(Integer prepTimeMinutes) {
        this.prepTimeMinutes = prepTimeMinutes;
    }

    public Integer getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(Integer cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public DifficultyType getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyType difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getEstimatedCalories() {
        return estimatedCalories;
    }

    public void setEstimatedCalories(Integer estimatedCalories) {
        this.estimatedCalories = estimatedCalories;
    }

    public Double getAverageRating() {
        if (ratings.isEmpty()) {
            return null;
        }
        Rating accumulator = new Rating();
        accumulator.setValue(0);
        int sum = ratings.stream()
                .reduce(accumulator, (a, b) -> {
                    a.setValue(a.getValue() + b.getValue());
                    return a;
                }).getValue();
        return (double) sum / ratings.size();
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = new ArrayList<>(tags);
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = new ArrayList<>(ratings);
    }

    public List<DietaryPreferenceType> getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setDietaryPreferences(List<DietaryPreferenceType> dietaryPreferences) {
        this.dietaryPreferences = new ArrayList<>(dietaryPreferences);
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }
}
