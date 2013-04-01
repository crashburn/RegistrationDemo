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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

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

	@NotBlank
    @Size(max=30)
	private String name;
	
	@NotNull
	private GradeLevel minGradeLevel;

	@NotNull
	private GradeLevel maxGradeLevel;

	@Embedded
	@NotNull
	@Valid
	private Address address;

	// Constructors:
	public School() {
		address = new Address();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	// String Representation:
	@Override
	public String toString() {
		return  "name: " + getName() + 
				", street: " + getAddress().getStreet() + 
				", city: " + getAddress().getCity() + 
				", state: " + getAddress().getState() + 
				", zip: " + getAddress().getZip() + 
				", min: " + minGradeLevel + 
				", max: " + maxGradeLevel;
	}

}
