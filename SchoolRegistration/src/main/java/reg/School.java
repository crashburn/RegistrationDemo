package reg;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class School {
	private static final long serialVersionUID = 1L;
	   
	// Persistent Fields:
	@Id
	@GeneratedValue
	Long id;

	private String name;
	private String city;
	private String state;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	private List<Student> students;

	// Constructors:
	public School() {
	}

	public School(String name, String city, String state) {
		this.name = name;
	    this.city = city;
	    this.state = state;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	// String Representation:
	@Override
	public String toString() {
		return "name: " + name + ", city: " + city + ", state: " + state;
	}

}
