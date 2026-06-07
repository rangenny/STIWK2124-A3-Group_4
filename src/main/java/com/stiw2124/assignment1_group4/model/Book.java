package com.stiw2124.assignment1_group4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter book title!")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Author Name is required!")
    @Size(max = 50)
    private String author;

    @NotBlank(message = "Category is required!")
    @Size(max = 30)
    private String category;

    @NotBlank(message = "Please enter short description!")
    @Size(max = 255)
    private String shortDesc;

    public Book() {
    }

    public Book(Long id, String title, String author, String category, String shortDesc) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.shortDesc = shortDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
}