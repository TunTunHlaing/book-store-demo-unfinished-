package com.example.bookstoredemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends IdClass{

    @NotBlank(message = "Name cannot be blank!!!")
    @NotEmpty(message = "Name cannot be empty!!!")
    @Column(name = "categroy_name")
    private String categoryName;


    @OneToMany(mappedBy = "category")
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book){
        book.setCategory(this);
        bookList.add(book);
    }
}
