package com.example.bookstoredemo.ds;

import com.example.bookstoredemo.dao.BookDao;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SessionScope
@Component
public class CartBean {

    private Set<BookDto> bookDtos = new HashSet<>();

    public void addToCart(BookDto bookDto){
        bookDtos.add(bookDto);
    }

    public void clearCart(){
        this.bookDtos.clear();
    }

    public int cartSize(){
        return bookDtos.size();
    }

    public void removeBook(BookDto bookDto){
        bookDtos.remove(bookDto);
    }

    public Set<BookDto> listAllCarts(){
        return bookDtos;
    }
}
