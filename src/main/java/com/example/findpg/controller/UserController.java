package com.example.findpg.controller;

import com.example.findpg.DAO.UserDAO;
import com.example.findpg.GenericMethods.GenericMethods;
import com.example.findpg.entity.LoginRequest;
import com.example.findpg.entity.User;
import com.example.findpg.genericactionresponse.GenericActionResponse;
import com.example.findpg.repository.UserRepository;
import com.example.findpg.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping("/user_controller")
public class UserController {

    @Autowired private UserDAO userDAO;

    @Autowired private UserRepository userRepository;

    @Autowired private AuthService authService;

    private GenericActionResponse<User> validateUser(User user) {
        GenericActionResponse<User> response = new GenericActionResponse<>(false);
        if (GenericMethods.isNullOrEmpty(user.getName(), true)) {
            response.setErrmsg("Name cannot be empty.");
            return response;
        }

        if (GenericMethods.isNullOrEmpty(user.getEmail(), true)) {
            response.setErrmsg("Email cannot be empty.");
            return response;
        } else if (!GenericMethods.regexChecker(user.getEmail(), new String[] {"Email"}, false)) {
            response.setErrmsg("Enter correct email.");
            return response;
        }

        if (GenericMethods.isNullOrEmpty(user.getPhone_number(), true)) {
            response.setErrmsg("Phone Number cannot be empty.");
            return response;
        }

        if (GenericMethods.isNullOrEmpty(user.getAddress(), true)) {
            response.setErrmsg("Address cannot be empty.");
            return response;
        }

        if (GenericMethods.isNullOrEmpty(user.getLanguages(), true)) {
            response.setErrmsg("Languages cannot be empty.");
            return response;
        }

        if (GenericMethods.isNullOrEmpty(user.getRole(), true)) {
            response.setErrmsg("Role cannot be empty.");
            return response;
        }

        response.setSuccess(true);
        return response;
    }

    @PostMapping("/add")
    private GenericActionResponse<User> add(@RequestParam("phone_number") String phone_number) {
        GenericActionResponse<User> response = new GenericActionResponse<>(false);

        if (Objects.requireNonNull(checkIfAlreadyExisted(phone_number).getBody()).getPhone_number()
                == null) {
            userDAO.addUser(phone_number);
        } else {
            userDAO.updateOTP(phone_number);
        }

        User user = userDAO.getUserByPhoneNumber(phone_number);
        int user_id = user.getId();

        response.setSuccess(true);
        response.setSuccessmsg(String.valueOf(user_id));

        return response;
    }

    @PostMapping("/getOTP")
    private int getOTP(@RequestParam("phone_number") String phone_number) {
        return userDAO.getOTP(phone_number);
    }

    @PostMapping("/checkIfAlreadyExisted")
    private ResponseEntity<User> checkIfAlreadyExisted(
            @RequestParam("phone_number") String phone_number) {
        User allUsers = userDAO.getUserByPhoneNumber(phone_number);
        return ResponseEntity.ok(Objects.requireNonNullElseGet(allUsers, User::new));
    }

    @PostMapping("/updateUserDetails")
    private GenericActionResponse<User> updateUserDetails(@RequestBody User user) {
        GenericActionResponse<User> response = validateUser(user);

        if (!response.getSuccess()) {
            return response;
        }

        User user1 =
                userDAO.updateUserDetails(
                        user.getName(),
                        user.getEmail(),
                        user.getPhone_number(),
                        user.getImageURL(),
                        user.getAddress(),
                        user.getLanguages(),
                        user.getRole());

        if (!GenericMethods.isNullOrEmpty(user1.getName(), true)) {
            boolean googleLogin;
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(user1.getEmail());
            if (!GenericMethods.isNullOrEmpty(user1.getPassword(), true)) {
                loginRequest.setPassword(user1.getPassword());
                googleLogin = false;
            } else {
                loginRequest.setPassword("");
                googleLogin = true;
            }
            authService.userLogin(loginRequest, googleLogin);

            response.setSuccess(true);
        } else {
            response.setErrmsg("Failed");
            response.setSuccess(false);
        }
        return response;
    }

    @PostMapping("/getByUserId")
    private User getByUserId(@RequestParam("user_id") String user_id) {
        return userDAO.getByUserId(user_id);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<User> fileUpload(
            @RequestParam("image") MultipartFile image, @RequestParam("user_id") String user_id) {
        User user;
        try {
            user = userDAO.uploadImage(image, user_id);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/profileImages/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {
        InputStream is = userDAO.getImageResource(imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
    }

    @PostMapping("/getByEmail")
    public User getByEmail(@RequestParam("email") String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @PostMapping("/updatePassword")
    public User updatePassword(
            @RequestParam("password") String password,
            @RequestHeader("Authorization") String token) {
        return userDAO.updatePassword(password, token.substring(7));
    }
}
