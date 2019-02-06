package hbcrud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="programmers")
public class Programmer {
	
	@Id
	@GeneratedValue
	@Column(name="Programmer_id")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Qualification")
	private String qualification;
	
	public Programmer() {}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLocation(String location) {
		this.location=location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setQualification(String qualification) {
		this.qualification=qualification;
	}
	
	public String getQualification() {
		return qualification;
	}

}
