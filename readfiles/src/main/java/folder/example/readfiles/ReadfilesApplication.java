package folder.example.readfiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import folder.example.readfiles.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})

public class ReadfilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadfilesApplication.class, args);
	}

}
