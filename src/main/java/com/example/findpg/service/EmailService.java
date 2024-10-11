package com.example.findpg.service;

import com.example.findpg.DAO.EmailDAO;
import com.example.findpg.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailDAO {
    @Autowired private JavaMailSender javaMailSender;
    @Autowired private JwtUtil jwtUtil;

    @Override
    public void sendEmail(String email) {
        String jwtToken = jwtUtil.generateTokenForPasswordReset(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("mehulkedia30@gmail.com");
        message.setSubject("Reset Link");
        message.setText(
                "Password reset link for email-id: "
                        + email
                        + " is http://localhost:3000/resetPassword?token="
                        + jwtToken);
        javaMailSender.send(message);
    }
}
