package com.example.findpg.controller;

import com.example.findpg.entity.LoginRequest;
import com.example.findpg.entity.LoginResponse;
import com.example.findpg.entity.User;
import com.example.findpg.repository.UserRepository;
import com.example.findpg.service.AuthService;
import com.example.findpg.GenericMethods.GenericMethods;
import com.example.findpg.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @Autowired private UserRepository userRepository;

    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("googleLogin") boolean googleLogin) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        User authenticatedUser = authService.userLogin(loginRequest, googleLogin);

        if (GenericMethods.isNullOrEmpty(authenticatedUser.getName(), true)) {
            return ResponseEntity.notFound().build();
        }

        String jwtToken = jwtUtil.generateToken(authenticatedUser);

        ResponseCookie cookie =
                ResponseCookie.from("token", jwtToken)
                        .httpOnly(true)
                        .secure(false)
                        .sameSite("Strict")
                        .build();

        LoginResponse loginResponse =
                new LoginResponse()
                        .setToken(jwtToken)
                        .setExpiresIn(authService.getExpirationTime());
        return ResponseEntity.ok().header("Set-Cookie", cookie.toString()).body(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @RequestParam("email") String email, @RequestParam("password") String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            User registeredUser = authService.userSignup(loginRequest);
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.ok(existingUser.orElseThrow());
        }
    }

    @GetMapping("/verifyToken")
    public boolean verifyToken(@RequestHeader("Authorization") String token) {
        return !jwtUtil.isTokenExpired(token.split(" ")[1]);
    }
}
