package com.example.findpg.repository;

import com.example.findpg.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageUserRepository extends JpaRepository<User, Long> {}
