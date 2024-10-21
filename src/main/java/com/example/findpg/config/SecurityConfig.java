package com.example.findpg.config;

import com.example.findpg.service.CustomUserDetailsService;
import com.example.findpg.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired private JwtUtil jwtUtil;

    @Autowired private CustomUserDetailsService userDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(
                                                "/auth/**",
                                                "/user_controller/profileImages/{imageName}",
                                                "city_state_controller/getAll",
                                                "image_controller/uploads/{imageName}",
                                                "user_controller/uploadImage",
                                                "user_controller/updateUserDetails",
                                                "pg_controller/getAllLocations",
                                                "email_controller/sendEmail")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated())
                .addFilterBefore(
                        jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user")
                        .password(passwordEncoder().encode("password"))
                        .roles("USER")
                        .build());
        return manager;
    }
}
