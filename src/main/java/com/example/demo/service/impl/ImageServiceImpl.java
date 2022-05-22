package com.example.demo.service.impl;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void delete(Set<Image> images){
        for(Image image: images)
            imageRepository.delete(image);
    }

}
