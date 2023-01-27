package com.example.bookstoredemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Customer extends IdClass{

    @Column(unique = true)
    private String username;
    private String password;
    @Embedded
    private Address address;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrderBook> customerOrderBooks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(username, customer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public void addRoles(Role role){
        role.getCustomers().add(this);
        roles.add(role);
    }

    public void addCustomerOrderBook(CustomerOrderBook customerOrderBook){
        customerOrderBook.setCustomer(this);
        customerOrderBooks.add(customerOrderBook);
    }


}
