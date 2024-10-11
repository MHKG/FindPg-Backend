package com.example.findpg.service;

import com.example.findpg.DAO.ImageDAO;
import com.example.findpg.entity.Images;
import com.example.findpg.repository.ImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class ImageService implements ImageDAO {

    private final String uploadDir = "uploads/";

    @PersistenceContext private EntityManager entityManager;

    @Autowired private Environment environment;

    @Autowired private ImageRepository imageRepository;

    @Override
    public Images uploadImage(MultipartFile image, String pg_id, String room_id)
            throws IOException {

        long millis = System.currentTimeMillis();

        String fileName = millis + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, image.getBytes());

        Images images = new Images();
        images.setImage(filePath.toString());

        if (pg_id != null && !pg_id.isEmpty()) {
            images.setPg_id(Integer.parseInt(pg_id));
        } else {
            images.setPg_id(null);
        }

        if (room_id != null && !room_id.isEmpty()) {
            images.setRoom_id(Integer.parseInt(room_id));
        } else {
            images.setRoom_id(null);
        }

        return imageRepository.save(images);
    }

    @Override
    public InputStream getImageResource(String fileName) throws FileNotFoundException {
        String fullPath = uploadDir + File.separator + fileName;
        return new FileInputStream(fullPath);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Images> getByPgId(String pg_id) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getImagesByPgId"), Images.class)
                        .setParameter("pg_id", pg_id);
        return (List<Images>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Images> getImagesByPgIdAndRoomId(String pg_id, String room_id) {
        Query query =
                entityManager
                        .createNativeQuery(
                                environment.getProperty("getImagesByPgIdAndRoomId"), Images.class)
                        .setParameter("pg_id", pg_id)
                        .setParameter("room_id", room_id);
        return (List<Images>) query.getResultList();
    }

    @Override
    public void removeImage(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);

        Images images = imageRepository.findByImage(filePath);

        imageRepository.delete(images);
    }
}
