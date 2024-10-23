package com.example.findpg.service;

import com.example.findpg.DAO.UserDAO;
import com.example.findpg.GenericMethods.GenericMethods;
import com.example.findpg.entity.User;
import com.example.findpg.repository.ImageUserRepository;
import com.example.findpg.repository.UserRepository;
import com.example.findpg.util.JwtUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Transactional
public class UserService implements UserDAO {

    private final String uploadDir = "profileImages/";

    @PersistenceContext private EntityManager entityManager;

    @Autowired private Environment environment;

    @Autowired private ImageUserRepository imageUserRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private JwtUtil jwtUtil;

    @Autowired private UserRepository userRepository;

    @Override
    public void addUser(String phone_number) {
        String generatedOTP = String.valueOf(Math.floor(1000 + Math.random() * 9000));
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("addUser"))
                        .setParameter("phone_number", phone_number)
                        .setParameter("otp", generatedOTP);
        query.executeUpdate();
    }

    @Override
    public User getUserByPhoneNumber(String phone_number) {
        Query query =
                entityManager.createNativeQuery(
                        environment.getProperty("getByPhoneNumber"), User.class);
        query.setParameter("phone_number", phone_number);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void updateOTP(String phone_number) {
        String generatedOTP = String.valueOf(Math.floor(1000 + Math.random() * 9000));
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("updateOTP"))
                        .setParameter("otp", generatedOTP)
                        .setParameter("phone_number", phone_number);
        query.executeUpdate();
    }

    @Override
    public int getOTP(String phone_number) {
        Query query =
                entityManager
                        .createNativeQuery(
                                "select otp from user where phone_number = :phone_number")
                        .setParameter("phone_number", phone_number);
        return (int) query.getSingleResult();
    }

    @Override
    public User updateUserDetails(
            String name,
            String email,
            String phone_number,
            String imageUrl,
            String address,
            String languages,
            String role) {

        User user = userRepository.findByEmail(email).orElseThrow();
        user.setName(name);
        user.setAddress(address);
        user.setLanguages(languages);
        user.setRole(role);
        user.setPhone_number(phone_number);
        if (GenericMethods.isNullOrEmpty(user.getImageURL(), true)) {
            user.setImageURL(imageUrl);
        }

        return userRepository.save(user);
    }

    @Override
    public User getByUserId(String user_id) {
        Query query =
                entityManager.createNativeQuery(environment.getProperty("getByUserId"), User.class);
        query.setParameter("user_id", user_id);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User uploadImage(MultipartFile image, String user_id) throws IOException {

        String fileName =
                user_id + "." + Objects.requireNonNull(image.getOriginalFilename()).split("\\.")[1];
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, image.getBytes());

        User user = getByUserId(user_id);
        user.setImageURL(filePath.toString());

        return imageUserRepository.save(user);
    }

    @Override
    public InputStream getImageResource(String fileName) throws FileNotFoundException {
        String fullPath = uploadDir + File.separator + fileName;
        return new FileInputStream(fullPath);
    }

    @Override
    public User updatePassword(String password, String token) {
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
