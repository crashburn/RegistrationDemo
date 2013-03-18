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

@Entity
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
		return address.getState();
	}

	public void setZip(String zip) {
		address.setZip(zip);
	}

	// String Representation:
	@Override
	public String toString() {
		return  "name: " + getName() + 
				", city: " + getCity() + 
				", state: " + getState() + 
				", min: " + minGradeLevel + 
				", max: " + maxGradeLevel +
				", isInRange(L7): " + GradeLevel.L7.isInRange(minGradeLevel, maxGradeLevel);
	}

}
