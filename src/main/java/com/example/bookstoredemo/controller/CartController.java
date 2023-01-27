package com.example.bookstoredemo.controller;

import com.example.bookstoredemo.ds.BookDto;
import com.example.bookstoredemo.entity.Customer;
import com.example.bookstoredemo.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") int id){

        cartService.addToCart(id);
        return "redirect:/user/book?id="+ id;
    }

    @GetMapping("/view")
    public String viewCart(Model model){

        model.addAttribute("bookDto",new BookDto());
        model.addAttribute("carts",cartService.listCart());
        return "cart-view";
    }

    @GetMapping("/delete")
    public String removeFromCard(@RequestParam("id")int id){
     cartService.removeFormCart(findBookById(id));
     return "redirect:/cart/view";
    }

    @GetMapping("/clear")
    public String clearCart(){
        cartService.clearCart();
        return "redirect:/cart/view";
    }

    private List<Integer> bookQuantityList = new ArrayList<>();


    @PostMapping("/checkout")
    public String checkOut(BookDto bookDto){
        this.bookQuantityList = bookDto.getItemsList();
        System.out.println("================" + bookQuantityList);

        return "redirect:/cart/register-form";
    }

    @GetMapping("/register-form")
    public String registerForm(Model model){
        model.addAttribute("customer",new Customer());
        return "register";
    }

    @PostMapping("/register-form")
    public String saveRegisteredCustomer(@Valid Customer customer,BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "register";
        }

        Set<BookDto> carts = cartService.listCart();
        int index = 0;

        for (BookDto bookDto: carts){
           bookDto.setQuantity(bookQuantityList.get(index));
           index++;
        }

        cartService.register(customer,carts);

        System.out.println("Carts......"+ carts);

        return "redirect:/login";

    }

    private BookDto findBookById(int id) {
        return cartService.listCart().stream().filter(b -> b.getId() == id).findFirst().get();
    }

    @ModelAttribute("carts")
    public Set<BookDto> bookDtos(){
        return cartService.listCart();
    }

}
