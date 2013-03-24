/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package reg;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="Schools_name",
                query="SELECT s FROM School s ORDER BY s.name"),
    @NamedQuery(name="Schools_city",
                query="SELECT s FROM School s ORDER BY s.address.city"),
    @NamedQuery(name="Schools_state",
                query="SELECT s FROM School s ORDER BY s.address.state"),
    @NamedQuery(name="Schools_zip",
                query="SELECT s FROM School s ORDER BY s.address.zip"),
}) 
public class School {
	private static final long serialVersionUID = 1L;
	   
	// Persistent Fields:
	@Id
	@GeneratedValue
	Long id;

	private String name;
	private GradeLevel minGradeLevel;
	private GradeLevel maxGradeLevel;

	@Embedded
	private Address address;

	// Constructors:
	public School() {
		address = new Address();
	}

	public School(String name, GradeLevel aMinGradeLevel, GradeLevel aMaxGradeLevel, Address anAddress) {
		this.name = name;
		this.minGradeLevel = aMinGradeLevel;
		this.maxGradeLevel = aMaxGradeLevel;
		this.address = anAddress;
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

	public GradeLevel getMinGradeLevel() {
		return minGradeLevel;
	}
	
	public void setMinGradeLevel(GradeLevel aGradeLevel) {
		minGradeLevel = aGradeLevel;
	}

	public GradeLevel getMaxGradeLevel() {
		return maxGradeLevel;
	}
	
	public void setMaxGradeLevel(GradeLevel aGradeLevel) {
		maxGradeLevel = aGradeLevel;
	}
	
	public String getStreet() {
		return address.getStreet();
	}

	public void setStreet(String street) {
		address.setStreet(street);
	}

	public String getCity() {
		return address.getCity();
	}

	public void setCity(String city) {
		address.setCity(city);
	}

	public String getState() {
		return address.getState();
	}

	public void setState(String state) {
		address.setState(state);
	}

	public String getZip() {
		return address.getZip();
	}

	public void setZip(String zip) {
		address.setZip(zip);
	}

	// String Representation:
	@Override
	public String toString() {
		return  "name: " + getName() + 
				", street: " + getStreet() + 
				", city: " + getCity() + 
				", state: " + getState() + 
				", zip: " + getZip() + 
				", min: " + minGradeLevel + 
				", max: " + maxGradeLevel;
	}

}
