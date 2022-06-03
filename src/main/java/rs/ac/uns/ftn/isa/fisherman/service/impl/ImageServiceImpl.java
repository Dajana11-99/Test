package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.repository.ImageRepository;
import rs.ac.uns.ftn.isa.fisherman.service.ImageService;

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
