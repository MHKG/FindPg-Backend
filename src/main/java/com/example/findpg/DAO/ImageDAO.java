package com.example.findpg.DAO;

import com.example.findpg.entity.Images;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ImageDAO {

    Images uploadImage(MultipartFile image, String pg_id, String room_id) throws IOException;

    InputStream getImageResource(String fileName) throws FileNotFoundException;

    List<Images> getByPgId(String pg_id);

    List<Images> getImagesByPgIdAndRoomId(String pg_id, String room_id);

    void removeImage(String filePath) throws IOException;
}
