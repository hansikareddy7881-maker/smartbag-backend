
package com.smartbag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "bag_items")
public class BagItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bag item name is required")
    @Column(nullable = false)
    private String name;

    private String category;

    @PositiveOrZero(message = "Weight cannot be negative")
    private double weight;

    private String description;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public BagItem() {
    }

    public BagItem(String name, String category,
                   double weight, String description) {
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
