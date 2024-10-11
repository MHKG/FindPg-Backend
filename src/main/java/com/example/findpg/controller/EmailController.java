package com.example.findpg.controller;

import com.example.findpg.DAO.EmailDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email_controller")
public class EmailController {

    @Autowired private EmailDAO emailDAO;

    @PostMapping("/sendEmail")
    public ResponseEntity sendEmail(@RequestParam("email") String email) {
        emailDAO.sendEmail(email);
        return ResponseEntity.ok().build();
    }
}
