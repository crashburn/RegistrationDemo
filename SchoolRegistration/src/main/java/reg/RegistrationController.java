/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package reg;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	
   @Autowired
   private SchoolDao schoolDao;

   @Autowired
   private StudentDao studentDao;
   
   Logger logger = Logger.getLogger(SchoolDao.class.getName());

   @RequestMapping(value = "/schools")
   public ModelAndView getSchools(HttpServletRequest request) {
      return new ModelAndView("schools.jsp", "schoolDao", schoolDao);
   }

   @RequestMapping(value = "/viewschool")
   public ModelAndView viewSchool(HttpServletRequest request) {
	   String in = request.getParameter("id");
	   try {
		   long id = Long.parseLong(in);
		   return new ModelAndView("school.jsp", "school", schoolDao.retrieve(id));
	   }
	   catch(NumberFormatException nfe) {
		   logger.severe("could not interpret school id: " + in);
		   return new ModelAndView("schools.jsp", "schoolDao", schoolDao);
	   }
   }

   @RequestMapping(value = "/deleteschool")
   public ModelAndView deleteSchool(HttpServletRequest request) {
	   String in = request.getParameter("id");
	   try {
		   long id = Long.parseLong(in);
		   schoolDao.delete(id);
	   }
	   catch(NumberFormatException nfe) {
		   logger.severe("could not interpret school id: " + in);
	   }
	   return new ModelAndView("schools.jsp", "schoolDao", schoolDao);
   }

   @RequestMapping(value = "/newschool", method = RequestMethod.POST)
   public ModelAndView createSchool(HttpServletRequest request) {
      // Handle a new guest (if any):
      String name = request.getParameter("name");
      String city = request.getParameter("city");
      String state = request.getParameter("state");
      if (name != null) {
    	  schoolDao.persist(new School(name, city, state));
      }
      return new ModelAndView("schools.jsp", "schoolDao", schoolDao);
   }

   @RequestMapping(value = "/students")
   public ModelAndView getStudents(HttpServletRequest request) {
      return new ModelAndView("students.jsp", "studentDao", studentDao);
   }
   
   @RequestMapping(value = "/viewstudent")
   public ModelAndView viewStudent(HttpServletRequest request) {
	   String in = request.getParameter("id");
	   try {
		   long id = Long.parseLong(in);
		   ModelAndView mav = new ModelAndView("student.jsp", "student", studentDao.retrieve(id));
		   mav.addObject("schoolDao", schoolDao);
		   return mav;
	   }
	   catch(NumberFormatException nfe) {
		   logger.severe("could not interpret student id: " + in);
		   return new ModelAndView("students.jsp", "studentDao", studentDao);
	   }
   }

   @RequestMapping(value = "/deletestudent")
   public ModelAndView deleteStudent(HttpServletRequest request) {
	   String in = request.getParameter("id");
	   try {
		   long id = Long.parseLong(in);
		   studentDao.delete(id);
	   }
	   catch(NumberFormatException nfe) {
		   logger.severe("could not interpret student id: " + in);
	   }
	   return new ModelAndView("students.jsp", "studentDao", studentDao);
   }

   @RequestMapping(value = "/newstudent", method = RequestMethod.POST)
   public ModelAndView createStudent(HttpServletRequest request) {
      // Handle a new guest (if any):
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      if (firstName != null) {
    	  studentDao.persist(new Student(firstName, lastName));
      }
      return new ModelAndView("students.jsp", "studentDao", studentDao);
   }


   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public ModelAndView register(HttpServletRequest request) {
      // Handle a new guest (if any):
      String studentIn = request.getParameter("studentId");
      String schoolIn = request.getParameter("choiceId");
      try {
    	  long studentId = Long.parseLong(studentIn);
    	  long schoolId = Long.parseLong(schoolIn);
    	  studentDao.register(studentId, schoolId);

    	  ModelAndView mav = new ModelAndView("student.jsp", "student", studentDao.retrieve(studentId));
		   mav.addObject("schoolDao", schoolDao);
		   return mav;
      }
	  catch(NumberFormatException nfe) {
		  logger.severe("could not interpret ids: " + studentIn + "," + schoolIn);
	  }
      return new ModelAndView("students.jsp", "studentDao", studentDao);
   }
}
