package com.example.bookstoredemo.ds;

import com.example.bookstoredemo.entity.Author;
import com.example.bookstoredemo.entity.Category;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class BookDto {

    private int id;
    private String title;
    private LocalDate yearPublished;
    private String publisher;
    private double price;
    private int quantity;
    private String genre;
    private String description;

    private Category category;

    private List<Integer> itemsList = new ArrayList<>();
    private Author author;

    private String imgUrl;

    public BookDto(int id, String title, LocalDate yearPublished, String publisher, double price, int quantity, String genre, String description, Category category, Author author, String imgUrl) {
        this.id = id;
        this.title = title;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
        this.description = description;
        this.category = category;
        this.author = author;
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return id == bookDto.id &&  Objects.equals(title, bookDto.title) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearPublished=" + yearPublished +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public BookDto() {
    }
}
