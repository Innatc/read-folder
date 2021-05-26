package folder.example.readfiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import org.springframework.http.MediaType;
import java.util.Arrays;
import java.util.stream.Collectors;
import folder.example.readfiles.model.RequestDetails;
import folder.example.readfiles.service.EventStorageService;

public class EventsReadController {

	    @Autowired
	    private EventStorageService eventStorageService;
	    
	 	    
	    @PostMapping(path="/uploadFile/{localuploadpath}",consumes = MediaType.APPLICATION_XML_VALUE,
	    		produces = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody RequestDetails uploadFile(@RequestParam("file") MultipartFile file,
	    	                                 @RequestBody RequestDetails requestDetails,
	    	                                 @PathVariable(value = "localuploadpath" ) String localuploadpath ) {
	    	
	    	System.out.println("File recived in location : " +  localuploadpath );
	    	
	        String fileName = eventStorageService.storeFile(file);	        
	        System.out.println("File : " +  fileName + " uploaded");
	        
	        RequestDetails requestDetailsSuccess= eventStorageService.storeInDB(requestDetails);	        
	        System.out.println("request id : " +  requestDetails.getId() + " stored in DB");
	        	        
	        return requestDetailsSuccess;
	    }

	    
	    @PostMapping(path="/uploadMultipleFiles/{localuploadpath}",consumes = MediaType.APPLICATION_XML_VALUE,
	    		produces = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody List<RequestDetails> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
	    		                                         @RequestBody List<RequestDetails> requestDetails,
                                                         @PathVariable(value = "localuploadpath" ) String localuploadpath) {
	    	
	    	// For every file call eventStorageService.storeFile and print file name
	    	 Arrays.asList(files).forEach((f )-> eventStorageService.storeFile(f));
	    	 
	    	
	    	 // Load every request to DB
	    	 List<RequestDetails> reqDetSuccessResponse = requestDetails.stream()
	    			                                              .map(req -> eventStorageService.storeInDB(req))
	    			                                              .collect(Collectors.toList());
	    		
	    	 return reqDetSuccessResponse;
	    			 
	    }
	
}
