package folder.example.readfiles.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;




@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "request_details")
@XmlRootElement(name = "RequestDetails")
public class RequestDetails {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "identifier")
	 @JsonIgnore
	 private Integer identifier;     
	 
     @Column(name = "id")
     @XmlElement(name = "Id")
 	 @JsonIgnore
	 private String id;  
     
     @Column(name = "accept_date")
     @XmlElement(name = "AcceptDate")
     @JsonIgnore
	 private Date acceptDate;
	
     @Column(name = "source_company")
     @XmlElement(name = "SourceCompany")
	 private String sourceCompany;
     
     @OneToMany(targetEntity = Event.class,cascade = CascadeType.ALL)  
     @XmlElement(name = "Event")
     private List<Event> event;
     
    // This constructor is needed to support @JsonIgnore option, only sourceCompany, List<EventResponse> eventResponse will be 
    // included into response
 	public RequestDetails(String sourceCompany, List<Event> event) {
 		super();
 		this.sourceCompany = sourceCompany;
 		this.event = event;
 	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getSourceCompany() {
		return sourceCompany;
	}

	public void setSourceCompany(String sourceCompany) {
		this.sourceCompany = sourceCompany;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "RequestDetails [identifier=" + identifier + ", id=" + id + ", acceptDate=" + acceptDate
				+ ", sourceCompany=" + sourceCompany + ", event=" + event + "]";
	}
	
     
     
	
}
