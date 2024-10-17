package com.example.findpg.service;

import com.example.findpg.entity.LoginRequest;
import com.example.findpg.entity.User;
import com.example.findpg.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Transactional
public class AuthService {
    @Autowired private UserRepository userRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private AuthenticationManager authenticationManager;

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration.time}")
    private long EXPIRATION_TIME;

    public User userLogin(LoginRequest loginRequest, boolean googleLogin) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        if (user.getPhone_number() == null) {
            return new User();
        }

        if (googleLogin) {
            return user;
        }

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return user;
        } else {
            return new User();
        }
    }

    public long getExpirationTime() {
        return EXPIRATION_TIME;
    }

    public User userSignup(LoginRequest loginRequest) {
        User user = new User();
        user.setEmail(loginRequest.getEmail());
        if (!Objects.equals(loginRequest.getPassword(), "")) {
            user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        }
        return userRepository.save(user);
    }
}
