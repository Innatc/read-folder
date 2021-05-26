package folder.example.readfiles.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import folder.example.readfiles.model.RequestDetails;

public interface RequestDetailsRepository extends JpaRepository<RequestDetails,Integer> {

	@Query("Select a from RequestDetails a where id = ?1")
	RequestDetails checkFilebyId (String id);
	
	
}
