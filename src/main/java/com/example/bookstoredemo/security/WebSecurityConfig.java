package com.example.bookstoredemo.security;

import com.example.bookstoredemo.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(customerUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Throwable{

        http.authorizeHttpRequests()

                .requestMatchers("/css/**","/bootstrap/**","/css/app.css","/user/**","/cart/**","/customer/**","/","/home","/cart/register-form")
                .permitAll()
                .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .and()
                .csrf().disable();

        return http.build();
    }


}
