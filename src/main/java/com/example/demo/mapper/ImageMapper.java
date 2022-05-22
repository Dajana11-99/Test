package com.example.demo.mapper;
import com.example.demo.dto.ImageDto;
import com.example.demo.model.Image;

import java.util.HashSet;
import java.util.Set;

public class ImageMapper {

    public Image imageDtoToImage(ImageDto image){
        return new Image(image.getId(),image.getUrl());
    }

    public ImageDto imageToImageDto(Image image) { return new ImageDto(image.getId(),image.getUrl());}

    public Set<Image> imageDtoSToImages(Set<ImageDto> imageDtoS){
        Set<Image> images = new HashSet<>();
        for(ImageDto imageDto: imageDtoS)
            images.add(imageDtoToImage(imageDto));
        return images;
    }
    public Set<ImageDto> imageToImageDtoS(Set<Image> images){
        Set<ImageDto> imagesDtoS = new HashSet<>();
        for(Image image: images)
            imagesDtoS.add(imageToImageDto(image));
        return imagesDtoS;
    }
}
