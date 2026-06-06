package com.stiw2124.assignment1_group4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 255, message = "Please enter book title!")
    private String title;

    @Size(min = 1, max = 50, message = "Author Name is required!")
    private String author;

    @Size(min = 1, max = 30, message = "Category is required!")
    private String category;

    @Size(min = 1, max = 255, message = "Please enter short description!")
    private String shortDesc;

    public Book() {
    }

    public Book(Long id, @Size(min = 1, max = 255, message = "Please enter book title!") String title,
            @Size(min = 1, max = 50, message = "Author Name is required!") String author,
            @Size(min = 1, max = 30, message = "Category is required!") String category,
            @Size(min = 1, max = 255, message = "Please enter short description!") String shortDesc) {
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
