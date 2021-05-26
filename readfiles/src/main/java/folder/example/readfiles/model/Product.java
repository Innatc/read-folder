package folder.example.readfiles.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "product")
@XmlRootElement(name = "Product")
public class Product {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "id")
	 private Integer id;     
	 
	 @Column(name = "type")
	 @XmlElement(name = "Type")
	 private String type;
	
	 @Column(name = "price")
	 @XmlElement(name = "Price")
	 private double  price;
	 
	 @Column(name = "start_date")
	 @XmlElement(name = "StartDate")
	 private Date startDate;
	 
	 @Column(name = "end_date")
	 @XmlElement(name = "EndDate")
	 private Date endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + ", price=" + price + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	 
	 
}
