package folder.example.readfiles.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import folder.example.readfiles.exception.FileStorageException;
import folder.example.readfiles.model.RequestDetails;
import folder.example.readfiles.property.FileStorageProperties;
import folder.example.readfiles.repository.RequestDetailsRepository;

@Service
public class EventStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	RequestDetailsRepository  requestDetailsRepo;

    @Autowired
   public EventStorageService(FileStorageProperties fileStorageProperties) {
    	
    
      this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
               .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the backup directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
        
            //Copy file to the target backup location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);                          
           
            return fileName;                 
            
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file in backup location" + fileName + ". Please try again!", ex);
        }
        
       
    }

    public RequestDetails storeInDB( RequestDetails requestDetails) {
    	
        //Check in DB if such file is already received
        RequestDetails reqDet = requestDetailsRepo.checkFilebyId(requestDetails.getId()) ;
        
        if(reqDet != null) {
        	throw new FileStorageException("Duplicate request recieved!! request id  : "+  requestDetails.getId() +" already exists in DB " );
        } else {

        	// Save in DB
            requestDetailsRepo.save(requestDetails);                             
        }    
        
        return requestDetails;
    }
    
	
}
