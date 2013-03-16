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
