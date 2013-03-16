package reg;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	private static final long serialVersionUID = 1L;
	   
	// Persistent Fields:
	@Id
	@GeneratedValue
	Long id;

	private String firstName;
	private String lastName;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolId")
	private School school;

	// Constructors:
	public Student() {
	}

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
	    this.lastName = lastName;
	}
	
	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	// String Representation:
	@Override
	public String toString() {
		return "first name: " + firstName + ", last name: " + lastName;
	}

}
