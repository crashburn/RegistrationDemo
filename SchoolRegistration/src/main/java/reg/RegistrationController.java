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
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	
	private static final int PAGE_SIZE = 5;
	
   @Autowired
   private SchoolDao schoolDao;

   @Autowired
   private StudentDao studentDao;
   
   Logger logger = Logger.getLogger(RegistrationController.class.getName());

   @RequestMapping(value = "/schools", method = RequestMethod.GET)
   public String getSchools(@ModelAttribute TableState tableState, Model model) {
	   
	   // Adjust the table state
	   tableState.setDefaultSortBy("name");
	   tableState.setMaxPageIndex(getMaxPageIndex(schoolDao.getSchoolCount()));
		   
	   // Populate the schools
	   List<School> schools = schoolDao.getAllSchools(tableState.getPageIndex(), PAGE_SIZE, tableState.getSortBy());
	   model.addAttribute("schools", schools);

	   return "schools.jsp";
   }

   @RequestMapping(value = "/schools", method = RequestMethod.POST)
   public String addSchool(@ModelAttribute School school) {

	   // Persist the new school
	   schoolDao.persist(school);
	   return "redirect:/schools.html";
   }

   @RequestMapping(value = "/schools/new", method = RequestMethod.GET)
   public String initNewSchool() {
	   return "newschool.jsp";
   }
    
   @RequestMapping(value = "/schools/{schoolId}/detail", method = RequestMethod.GET)
   public String viewSchool(@ModelAttribute TableState tableState, @PathVariable long schoolId, Model model) {

	   // Populate the school
	   School school = schoolDao.retrieve(schoolId);
	   model.addAttribute(school);

	   //Adjust the table state
	   tableState.setDefaultSortBy("lastName");
	   tableState.setMaxPageIndex(getMaxPageIndex(studentDao.getStudentCountBySchool(school)));

	   // Populate the students
	   List<Student> students = studentDao.getStudentsBySchool(school, tableState.getPageIndex(), PAGE_SIZE, tableState.getSortBy());
	   model.addAttribute("students", students);
	   
	   return "school.jsp";
   }

   @RequestMapping(value = "/schools/{schoolId}/detail", method = RequestMethod.POST, params="verb=delete")
   public String deleteSchool(@PathVariable long schoolId) {
	   studentDao.unenrollStudentsBySchool(schoolId);
	   schoolDao.delete(schoolId);
	   return "redirect:/schools.html";
   }

   @RequestMapping(value = "/schools/{schoolId}/detail", method = RequestMethod.POST, params="verb=edit")
   public String editSchool(@PathVariable long schoolId) {
	   return "redirect:/schools/" + schoolId + "/detail.html";
   }

   @RequestMapping(value = "/students", method = RequestMethod.GET)
   public String getStudents(@ModelAttribute TableState tableState, Model model) {
	   
	   // Adjust the table state
	   tableState.setDefaultSortBy("lastName");
	   tableState.setMaxPageIndex(getMaxPageIndex(studentDao.getStudentCount()));
		   
	   // Populate the schools
	   List<Student> students = studentDao.getAllStudents(tableState.getPageIndex(), PAGE_SIZE, tableState.getSortBy());
	   model.addAttribute("students", students);

      return "students.jsp";
   }
   
   @RequestMapping(value = "/students/{studentId}/detail", method = RequestMethod.GET)
   public String viewStudent(@PathVariable long studentId, Model model) {

	   // Populate the school
	   Student student = studentDao.retrieve(studentId);
	   model.addAttribute(student);
	   
	   List<School> schools = schoolDao.getAllSchoolsByZipAndGradeLevel(student.getAddress().getZip(), student.getGradeLevel());
	   model.addAttribute("schools", schools);

	   return "student.jsp";
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


   @RequestMapping(value = "/students/{studentId}/detail", method = RequestMethod.POST, params="verb=register")
   public String register(@PathVariable long studentId, @RequestParam long schoolId) {

	   studentDao.register(studentId, schoolId);
	   return "redirect:/students.html";
   }
   
   private int getMaxPageIndex(long totalRecordCount) {
	   long max = totalRecordCount / PAGE_SIZE;
	   long mod = totalRecordCount % PAGE_SIZE;
	   if( (max > 0) && (mod == 0) ) {
		   max--;
	   }
	   return (int) max;
   }
}
