package com.example.parkingProject.config;

import com.example.parkingProject.constant.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){  //암호화 해주는 역할
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/informationUpdate").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/parking").permitAll()
                        .requestMatchers("/insertMember").permitAll()
                        .requestMatchers("/parkingState").permitAll()
                        .requestMatchers("/payment").permitAll()
                        .anyRequest().hasRole("ADMIN")
//                .anyRequest().authenticated()
                )
                .formLogin((form)->form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true))

                .logout(out-> out.logoutSuccessUrl("/")
                        .logoutUrl("/logout"))
                .csrf(csrf->csrf.disable());
        return http.build();
    }
}
