package rs.ac.uns.ftn.isa.fisherman.service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;

import java.io.IOException;

public interface FirebaseService {
     void download(String fileName) throws IOException;
     void uploadCabinImage(MultipartFile images,String cabinName) throws IOException;
     void uploadAdventureImage(MultipartFile newImage,String adventureName)throws IOException;

    void uploadBoatImage(MultipartFile multipartFile, Boat boat) throws IOException;
}
