package com.example.bookstoredemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class CustomerOrderBook extends IdClass{

    private String orderCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Book book;
}
