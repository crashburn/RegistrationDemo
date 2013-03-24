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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StudentDao {

	   @Autowired
	   private SchoolDao schoolDao;

	   Logger logger = Logger.getLogger(StudentDao.class.getName());
	
	// Injected database connection:
	   @PersistenceContext
	   private EntityManager em;

	   // Stores a new student:
	   @Transactional
	   public void persist(Student student) {
	      em.persist(student);
	   }
	   
	   public Student retrieve(long id) {
		   return em.find(Student.class, id);
	   }

	   // Retrieves all the students:
	   public List<Student> getAllStudents() {
		   TypedQuery<Student> query =
	            em.createQuery("SELECT s FROM Student s ORDER BY s.id", Student.class);
		   return query.getResultList();
	   }

	   // Retrieves a page of students by school:
	   public List<Student> getStudentsBySchool(School school, int aPageIndex, int aPageSize, String aSortBy) {
		   String sortBy = (aSortBy != null) ? aSortBy : "lastName";
		   TypedQuery<Student> query =
		   		em.createNamedQuery("Student.bySchool_" + sortBy, Student.class);
		   query.setParameter("school", school);
		   List<Student> results =
				      query.setFirstResult(aPageIndex * aPageSize)
				           .setMaxResults(aPageSize)
				           .getResultList();
		   return results;
	   }
	   
	   // Retrieves a count of students by school:
	   public long getStudentCountBySchool(School school) {
		   TypedQuery<Long> query =
				   em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.school = :school", Long.class);
		   query.setParameter("school", school);
		   return query.getSingleResult();
	   }
	   
	   @Transactional
	   public void unenrollStudentsBySchool(long schoolId) {
		   School school = schoolDao.retrieve(schoolId);
		   Query query = em.createQuery("UPDATE Student s SET s.school = :none WHERE s.school = :school");
		   query.setParameter("none", null);
		   query.setParameter("school", school);
		   query.executeUpdate();
	   }
	   
	   @Transactional
	   public void unenrollAllStudents() {
		   Query query = em.createQuery("UPDATE Student s SET s.school = :none");
		   query.setParameter("none", null);
		   query.executeUpdate();
	   }
	   
	   @Transactional
	   public void delete(long id) {
		   Student student = retrieve(id);
		   if(student != null) {
			   em.remove(student);
		   }
		   else {
			   logger.info("could not find student with id = " + id);
		   }
	   }

	   @Transactional
	   public void register(long studentId, long schoolId) {
		   Student student = retrieve(studentId);
		   School school = schoolDao.retrieve(schoolId);
		   if(student != null && school != null) {
			   student.setSchool(school);
			   em.persist(student);
		   }
	   }

}
