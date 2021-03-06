/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package reg;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Address {
	
	@NotBlank
    @Size(max=50)
	private String street;
	
	@NotBlank
    @Size(max=20)
	private String city;
	
	@Pattern(regexp="[A-Z]{2}", message="must be exactly two uppercase characters")
	private String state;

    @Pattern(regexp="[0-9]{5}", message="must be exactly 5 digits")
	private String zip;
	
	public Address() {
		
	}
	
	public Address(String aStreet, String aCity, String aState, String aZip) {
		this.street = aStreet;
		this.city 	= aCity;
		this.state	= aState;
		this.zip	= aZip;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String toString() {
		return street + ", " + city + ", " + state + ", " + zip;
	}

}
