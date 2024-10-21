package com.example.findpg.controller;

import com.example.findpg.DAO.ImageDAO;
import com.example.findpg.entity.Images;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/image_controller")
public class ImageController {

    @Autowired private ImageDAO imageDAO;

    @GetMapping(value = "/uploads/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {
        InputStream is = imageDAO.getImageResource(imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
    }

    @PostMapping("/uploadAll")
    public ResponseEntity<List<Images>> uploadAll(
            @RequestParam("file") MultipartFile[] files,
            @RequestParam("pg_id") String pg_id,
            @RequestParam("room_id") String[] room_id) {

        List<Images> img = new ArrayList<>();
        try {
            for (int i = 0; i < files.length; i++) {
                if (Objects.equals(room_id.length, 0)) {
                    img.add(imageDAO.uploadImage(files[i], pg_id, ""));
                } else {
                    img.add(imageDAO.uploadImage(files[i], pg_id, room_id[i]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(img, HttpStatus.OK);
    }

    @PostMapping("/getByPgId")
    public List<Images> getByPgId(@RequestParam("pg_id") String pg_id) {
        return imageDAO.getByPgId(pg_id);
    }

    @PostMapping("/getImagesByPgIdAndRoomId")
    public List<Images> getImagesByPgIdAndRoomId(
            @RequestParam("pg_id") String pg_id, @RequestParam("room_id") String room_id) {
        return imageDAO.getImagesByPgIdAndRoomId(pg_id, room_id);
    }

    @PostMapping("/remove_image")
    public void removeImage(@RequestParam("filePath") String filePath) throws IOException {
        imageDAO.removeImage(filePath);
    }
}
