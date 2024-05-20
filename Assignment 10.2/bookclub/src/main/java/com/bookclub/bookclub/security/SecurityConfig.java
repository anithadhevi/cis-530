package com.bookclub.bookclub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
    @Autowired
     public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
         .inMemoryAuthentication()
          .withUser("user")
          .password(encoder.encode("password"))
          .roles("USER")

          .and()
           .withUser("admin")
           .password(encoder.encode("admin123"))
           .roles("USER", "ADMIN");
           
    }

    @Bean 
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
         .authorizeHttpRequests((authz) -> authz
             .requestMatchers("/","/login").permitAll()
             .requestMatchers("/monthly-books/list","/monthly-books/new", "/monthly-books/new", "/montly-books").hasRole("ADMIN")
             .anyRequest().authenticated())
             
          .formLogin(form -> form
            .loginPage("/login")
            .successForwardUrl("/")
             .permitAll())
            
          
          .logout((logout) -> logout
            .logoutUrl("logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll());
            return http.build();
    }
}

 
 