package com.example.demo.controller;
import com.example.demo.dto.ImageDto;
import com.example.demo.model.Boat;
import com.example.demo.service.BoatService;
import com.example.demo.service.FirebaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping(value = "/firebase", produces = MediaType.APPLICATION_JSON_VALUE)
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;
    private final Logger logger= LoggerFactory.getLogger(FirebaseController.class);

    @Autowired
    private BoatService boatService;

    @PostMapping(value="/uploadCabinImage/{cabin}")
    public void uploadCabinImage(@PathVariable("cabin") String cabin,@RequestParam("file") MultipartFile multipartFile) {
        try {
            firebaseService.uploadCabinImage(multipartFile,cabin);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
    @PostMapping("/download")
    public void download(@RequestBody ImageDto img) throws IOException {
      //  firebaseService.download(img.getUrl());
    }

    @PostMapping(value="/uploadAdventureImage/{adventure}")
    public void uploadAdventureImage(@PathVariable("adventure") String adventure,@RequestParam("file") MultipartFile multipartFile) {
        try {
            firebaseService.uploadAdventureImage(multipartFile,adventure);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @PostMapping(value="/uploadBoatImage/{boatName}/{owner}")
    public String uploadBoatImage(@PathVariable("boatName") String boatName,@PathVariable("owner") Long owner,@RequestParam("file") MultipartFile multipartFile) {
        try {
            Boat boat=boatService.findByNameAndOwner(boatName,owner);
           return firebaseService.uploadBoatImage(multipartFile,boat);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return "Bad request";
    }


}
