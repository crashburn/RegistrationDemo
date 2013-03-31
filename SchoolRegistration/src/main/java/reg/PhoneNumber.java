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

@Embeddable
public class PhoneNumber {
	
    @Pattern(regexp="[0-9]{3}", message="must be exactly 3 digits")
	private String areaCode;

    @Pattern(regexp="[0-9]{3}", message="must be exactly 3 digits")
    private String exchange;
    
    @Pattern(regexp="[0-9]{4}", message="must be exactly 4 digits")
	private String subscriberNumber;
	
	public PhoneNumber() {
		
	}
	
	public PhoneNumber(String anAreaCode, String anExchange, String aSubscriberNumber) {
		this.areaCode = anAreaCode;
		this.exchange = anExchange;
		this.subscriberNumber = aSubscriberNumber;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	
	public String toString() {
		return areaCode + "-" + exchange + "-" + subscriberNumber;
	}

}
