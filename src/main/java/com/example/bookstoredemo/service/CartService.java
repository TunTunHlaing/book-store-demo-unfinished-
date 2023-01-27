package com.example.bookstoredemo.service;

import com.example.bookstoredemo.dao.BookDao;
import com.example.bookstoredemo.dao.CustomerDao;
import com.example.bookstoredemo.dao.CustomerOrderBookDao;
import com.example.bookstoredemo.dao.RoleDao;
import com.example.bookstoredemo.ds.BookDto;
import com.example.bookstoredemo.ds.CartBean;
import com.example.bookstoredemo.entity.Book;
import com.example.bookstoredemo.entity.Customer;
import com.example.bookstoredemo.entity.CustomerOrderBook;
import com.example.bookstoredemo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private CartBean cartBean;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CustomerOrderBookDao customerOrderBookDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addToCart(int id){
        cartBean.addToCart(toDto(bookDao.findById(id).get()));
    }

    public Set<BookDto> listCart(){
        return cartBean.listAllCarts();
    }

    public void clearCart(){
        cartBean.clearCart();
    }

    public void removeFormCart(BookDto bookDto){
        cartBean.removeBook(bookDto);
    }

    public BookDto toDto(Book book){

        return new BookDto(
          book.getId(),
                book.getTitle(),
                book.getYearPublished(),
                book.getPublisher(),
                book.getPrice(),
                book.getQuantity(),
                book.getGenre(),
                book.getDescription(),
                book.getCategory(),
                book.getAuthor(),
                book.getImgUrl()
        );

    }

    public void register(Customer customer, Set<BookDto> carts) {

            Customer managedCustomer = getCustomer(customer);

            CustomerOrderBook customerOrderBook =
                    new CustomerOrderBook();
            managedCustomer.addCustomerOrderBook(customerOrderBook);

            for (BookDto bookDto : carts) {
                Book book = toEntity(bookDto);
                book.addCustomerOrderBook(customerOrderBook);
            }

            customerOrderBookDao.save(customerOrderBook);

    }

    private Customer getCustomer(Customer customer) {

        Optional<Customer> customer1 = customerDao.findCustomerByUsername(customer.getUsername());

        if (!customer1.isPresent()) {
            Role customerRole = roleDao.findRoleByRoleName("ROlE_USER").get();

            customer.addRoles(customerRole);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));

            Customer managedCustomer = customerDao.save(customer);

            return managedCustomer;
        }

        else {

            return customer1.get();
        }
    }

    public Book toEntity(BookDto bookDto){

        return new Book(

                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getYearPublished(),
                bookDto.getPublisher(),
                bookDto.getPrice(),
                bookDto.getQuantity(),
                bookDto.getGenre(),
                bookDto.getDescription(),
                bookDto.getCategory(),
                bookDto.getAuthor(),
                bookDto.getImgUrl()
        );
    }


}
