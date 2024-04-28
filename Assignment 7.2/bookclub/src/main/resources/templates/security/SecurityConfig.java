package com.bookclub.bookclub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
 {
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        System.out.println("before auth");
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
         .inMemoryAuthentication()
          .withUser("user")
          .password("password")
          .roles("USER")

          .and()
           .withUser("admin")
           .password("admin123")
           .roles("USER", "ADMIN");
           
           System.out.println("end auth");


    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
         .authorizeHttpRequests((authz) -> authz
         .requestMatchers("/","/").permitAll()
         .anyRequest().authenticated())
          .formLogin(form -> form
            .loginPage("/login")
            .permitAll())
          .logout((logout) -> logout
            .logoutUrl("logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll());        

        return http.build();
    }
    
}
