package folder.example.readfiles.model;

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
@Table(name = "event")
@XmlRootElement(name = "Event")
public class Event {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "identifier")
	 @JsonIgnore
	 private Integer identifier;     
	 	 
     @Column(name = "id")
     @XmlElement(name = "Id")
     @JsonIgnore
	 private String id;     
	
	 @Column(name = "type")
	 @XmlElement(name = "Type")
	 @JsonIgnore
	 private String type;
	
	 @Column(name = "insured_id")
	 @XmlElement(name = "InsuredId")
	 private Integer insuredId;  
	 
	 @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
	 private List<Product> product;

	// This constructor is needed to support @JsonIgnore option, only insuredId, List<Product> product will be 
	// included into response
	public Event(Integer insuredId, List<Product> product) {
			super();
			this.insuredId = insuredId;
			this.product = product;
		}
	 
	public Event(Integer identifier, String id, String type, Integer insuredId, List<Product> product) {
		super();
		this.identifier = identifier;
		this.id = id;
		this.type = type;
		this.insuredId = insuredId;
		this.product = product;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getInsuredId() {
		return insuredId;
	}

	public void setInsuredId(Integer insuredId) {
		this.insuredId = insuredId;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Event [identifier=" + identifier + ", id=" + id + ", type=" + type + ", insuredId=" + insuredId
				+ ", product=" + product + "]";
	}
	 
	 
}
