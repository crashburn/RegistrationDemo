/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package reg;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SchoolDao {
		Logger logger = Logger.getLogger(SchoolDao.class.getName());
	
	// Injected database connection:
	   @PersistenceContext
	   private EntityManager em;

	   // Stores a new guest:
	   @Transactional
	   public void persist(School school) {
	      em.persist(school);
	   }
	   
	   public School retrieve(long id) {
		   return em.find(School.class, id);
	   }

	   // Retrieves all the guests:
	   public List<School> getAllSchools() {
		   TypedQuery<School> query =
	            em.createQuery("SELECT sch FROM School sch ORDER BY sch.id", School.class);
		   return query.getResultList();
	   }
	   
	   @Transactional
	   public void delete(long id) {
		   School school = retrieve(id);
		   if(school != null) {
			   em.remove(school);
		   }
		   else {
			   logger.info("could not find school with id = " + id);
		   }
	   }
}
