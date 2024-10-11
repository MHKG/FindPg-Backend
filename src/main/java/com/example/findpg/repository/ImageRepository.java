package com.example.findpg.repository;

import com.example.findpg.entity.Images;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long> {
    Images findByImage(String filePath);
}
