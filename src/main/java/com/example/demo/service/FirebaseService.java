package com.example.demo.service;
import com.example.demo.model.Boat;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

public interface FirebaseService {
     void download(String fileName) throws IOException;
     void uploadCabinImage(MultipartFile images,String cabinName) throws IOException;
     void uploadAdventureImage(MultipartFile newImage,String adventureName)throws IOException;

    String uploadBoatImage(MultipartFile multipartFile, Boat boat) throws IOException;
}
