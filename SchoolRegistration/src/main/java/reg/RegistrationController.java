/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package reg;

import java.util.GregorianCalendar;
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
      String street = request.getParameter("street");
      String city = request.getParameter("city");
      String state = request.getParameter("state");
      String zip = request.getParameter("zip");
      String minGL = request.getParameter("minGradeLevel");
      String maxGL = request.getParameter("maxGradeLevel");
      if (name != null) {
    	  GradeLevel minGradeLevel = GradeLevel.valueOf(minGL);
    	  GradeLevel maxGradeLevel = GradeLevel.valueOf(maxGL);
    	  Address address = new Address(street, city, state, zip);
    	  School newSchool = new School(name, minGradeLevel, maxGradeLevel, address);
    	  schoolDao.persist(newSchool);
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
      String sex = request.getParameter("sex");
      String gradeLevel = request.getParameter("gradeLevel");
      
      String birthMonth = request.getParameter("birthMonth");
      String birthDay = request.getParameter("birthDay");
      String birthYear = request.getParameter("birthYear");

      String street = request.getParameter("street");
      String city = request.getParameter("city");
      String state = request.getParameter("state");
      String zip = request.getParameter("zip");

      String areaCode = request.getParameter("areaCode");
      String exchange = request.getParameter("exchange");
      String subscriberNumber = request.getParameter("subscriberNumber");

      if (firstName != null) {
    	  Student newStudent = new Student();
    	  newStudent.setFirstName(firstName);
    	  newStudent.setLastName(lastName);
    	  newStudent.setSex(Sex.valueOf(sex));
    	  newStudent.setGradeLevel(GradeLevel.valueOf(gradeLevel));
    	  newStudent.setBirthdate(
    			  new GregorianCalendar(
    					  Integer.parseInt(birthYear)
    					  ,Integer.parseInt(birthMonth)-1
    					  ,Integer.parseInt(birthDay)
    					  )
    			  );
    	  newStudent.setAddress(
    			  new Address(street, city, state, zip)
    			  );
    	  newStudent.setPhoneNumber(new PhoneNumber(areaCode, exchange, subscriberNumber));
    	  
    	  studentDao.persist(newStudent);
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
