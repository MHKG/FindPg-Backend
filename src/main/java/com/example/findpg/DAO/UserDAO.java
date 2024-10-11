package com.example.findpg.DAO;

import com.example.findpg.entity.User;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface UserDAO {
    boolean addUser(String phone_number);

    User getUserByPhoneNumber(String phone_number);

    void updateOTP(String phone_number);

    int getOTP(String phone_number);

    boolean updateUserDetails(
            String name,
            String email,
            String phone_number,
            String imageUrl,
            String address,
            String languages,
            String role);

    User getByUserId(String user_id);

    User uploadImage(MultipartFile image, String user_id) throws IOException;

    InputStream getImageResource(String fileName) throws FileNotFoundException;

    User updatePassword(String password, String token);
}
