package com.example.findpg.service;

import com.example.findpg.entity.User;
import com.example.findpg.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =
                userRepository
                        .findByEmail(email) // Search by email
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "User not found with email: " + email));

        String password = user.getPassword() != null ? user.getPassword() : "DUMMY_PASSWORD";

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), password, new ArrayList<>());
    }
}
