package com.kristina.ecom.security.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.kristina.ecom.security.service.impl.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
          DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
          
          authProvider.setUserDetailsService(userDetailsService);
          authProvider.setPasswordEncoder(passwordEncoder());
      
          return authProvider;
    }    
    
    // @Bean
    // public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    //     return http.getSharedObject(AuthenticationManagerBuilder.class)
    //             .build();
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.csrf(csrf -> csrf.disable())
    //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/css/**", "/images/**", "/js/**", "/login", "/api/user/**").permitAll() 
    //             //.requestMatchers("/**").hasRole("ADMIN")
    //             .anyRequest().authenticated()              
    //         )
    //         .formLogin((form) -> form.permitAll());
        
    //     http.authenticationProvider(authenticationProvider());
        
    //     return http.build();
    // }   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
            .requestMatchers("/css/**", "/images/**", "/js/**", "/login", "/api/user/**").permitAll()  // Allow static resources and login page
            .requestMatchers("/**").hasRole("ADMIN")  // Require ADMIN role for all other requests
            .anyRequest().authenticated()
            )
            .formLogin((form) -> form
            .loginPage("/login")
            .defaultSuccessUrl("/", true)  // Redirect to home page after successful login
            .permitAll()
        );

        return http.build();
    }    

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String plainPassword = "mypass";
        String encodedPassword = encoder.encode(plainPassword);

        System.out.println("Plain Password: " + plainPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }

}

