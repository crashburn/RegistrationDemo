/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package reg;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="Students_lastName",
            	query="SELECT s FROM Student s ORDER BY s.lastName"),
	@NamedQuery(name="Students_firstName",
	            query="SELECT s FROM Student s ORDER BY s.firstName"),
	@NamedQuery(name="Students_sex",
	            query="SELECT s FROM Student s ORDER BY s.sex"),
	@NamedQuery(name="Students_school",
	            query="SELECT s FROM Student s LEFT JOIN s.school sch ORDER BY sch.name"),
	@NamedQuery(name="Students_gradeLevel",
	            query="SELECT s FROM Student s ORDER BY s.gradeLevel"),
    @NamedQuery(name="Student.bySchool_lastName",
                query="SELECT s FROM Student s WHERE s.school = :school ORDER BY s.lastName"),
    @NamedQuery(name="Student.bySchool_firstName",
                query="SELECT s FROM Student s WHERE s.school = :school ORDER BY s.firstName"),
    @NamedQuery(name="Student.bySchool_sex",
                query="SELECT s FROM Student s WHERE s.school = :school ORDER BY s.sex"),
    @NamedQuery(name="Student.bySchool_age",
                query="SELECT s FROM Student s WHERE s.school = :school ORDER BY s.birthdate"),
    @NamedQuery(name="Student.bySchool_gradeLevel",
                query="SELECT s FROM Student s WHERE s.school = :school ORDER BY s.gradeLevel"),
}) 
public class Student {
	private static final long serialVersionUID = 1L;
	   
	// Persistent Fields:
	@Id
	@GeneratedValue
	Long id;

	private String firstName;
	private String lastName;
	private Sex sex;
	private Calendar birthdate;
	private GradeLevel gradeLevel;
	
	@Embedded
	private Address address;

	@Embedded
	private PhoneNumber phoneNumber;
	
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
	
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public String getFormattedBirthdate(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(birthdate.getTime());
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public GradeLevel getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(GradeLevel gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	public int getAge() {
		Calendar now = Calendar.getInstance();
		// First get the raw diffs
		int yearDiff  = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
		int monthDiff = now.get(Calendar.MONTH) - birthdate.get(Calendar.MONTH);
		int dayDiff   = now.get(Calendar.DAY_OF_MONTH) - birthdate.get(Calendar.DAY_OF_MONTH);
		// Default the age to the year difference
		int age		  = yearDiff;
		// If the birth month hasn't been reached yet, reduce the age by 1 
		if(monthDiff  < 0) {
			age--;
		}
		// If the birth month is the current month, check if the day has been reached
		else if ( (monthDiff == 0) && (dayDiff < 0) ) {
			age--;
		}
		return age;
	}

	// String Representation:
	@Override
	public String toString() {
		return  "first name: " + firstName + 
				", last name: " + lastName +
				", birthdate: " + birthdate +
				", sex: " + sex +
				", grade level: " + gradeLevel;
	}

}
