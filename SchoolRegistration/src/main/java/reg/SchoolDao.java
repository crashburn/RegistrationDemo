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
	
	   @PersistenceContext
	   private EntityManager em;

	   @Transactional
	   public void persist(School school) {
	      em.persist(school);
	   }
	   
	   public School retrieve(long id) {
		   return em.find(School.class, id);
	   }

	   // Retrieves all the schools
	   public long getSchoolCount() {
		   TypedQuery<Long> query =
	            em.createQuery("SELECT COUNT(sch) FROM School sch", Long.class);
		   return query.getSingleResult();
	   }

	   // Retrieves all the schools
	   public List<School> getAllSchools() {
		   TypedQuery<School> query =
	            em.createQuery("SELECT sch FROM School sch ORDER BY sch.id", School.class);
		   return query.getResultList();
	   }

	   // Retrieves all the schools, with paging and sorting:
	   public List<School> getAllSchools(int aPageIndex, int aPageSize, String aSortBy) {
		   String sortBy = (aSortBy != null) ? aSortBy : "name";
		   TypedQuery<School> query =
		   		em.createNamedQuery("Schools_" + sortBy, School.class);
		   List<School> results =
				      query.setFirstResult(aPageIndex * aPageSize)
				           .setMaxResults(aPageSize)
				           .getResultList();
		   return results;
	   }

	   // Retrieves all the schools in a given zip code
	   public List<School> getAllSchoolsByZipAndGradeLevel(String zip, GradeLevel gl) {
		   TypedQuery<School> query =
	            em.createQuery(
	            		"SELECT sch FROM School sch " + 
	            		"WHERE sch.address.zip = :zip " +
	            		"AND sch.minGradeLevel <= :gl " +
	            		"AND sch.maxGradeLevel >= :gl " +
	            		"ORDER BY sch.name", School.class);
		   query.setParameter("zip", zip);
		   query.setParameter("gl", gl);
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
