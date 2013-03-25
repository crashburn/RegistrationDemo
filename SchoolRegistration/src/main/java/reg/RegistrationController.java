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
	   tableState.setMaxPageIndex((int)schoolDao.getSchoolCount(), PAGE_SIZE);
		   
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
	   tableState.setMaxPageIndex((int)studentDao.getStudentCountBySchool(school), PAGE_SIZE);

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
	   tableState.setMaxPageIndex((int)studentDao.getStudentCount(), PAGE_SIZE);
		   
	   // Populate the schools
	   List<Student> students = studentDao.getAllStudents(tableState.getPageIndex(), PAGE_SIZE, tableState.getSortBy());
	   model.addAttribute("students", students);

      return "students.jsp";
   }
   
   @RequestMapping(value = "/students", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute Student student, 
		   					@ModelAttribute Address address, 
		   					@ModelAttribute PhoneNumber phoneNumber,
		   					@RequestParam int birthMonth,
		   					@RequestParam int birthDay,
		   					@RequestParam int birthYear) {

	   // Combine the model objects
	   student.setAddress(address);
	   student.setPhoneNumber(phoneNumber);
	   student.setBirthdate(new GregorianCalendar(birthYear, birthMonth-1, birthDay));
	   
	   // Persist the new student
	   studentDao.persist(student);
	   
	   return "redirect:/students/" + student.getId() + "/school.html";
   }

   @RequestMapping(value = "/students/new", method = RequestMethod.GET)
   public String initNewStudent() {
	   return "newstudent.jsp";
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

   @RequestMapping(value = "/students/{studentId}/school", method = RequestMethod.GET)
   public String register(@PathVariable long studentId, Model model) {

	   Student student = studentDao.retrieve(studentId);
	   model.addAttribute("studentId", student.getId());
	   
	   School currentSchool = student.getSchool();
	   model.addAttribute("currentSchoolId", (currentSchool != null) ? currentSchool.getId() : Long.MIN_VALUE);

	   List<School> schools = schoolDao.getAllSchoolsByZipAndGradeLevel(student.getAddress().getZip(), student.getGradeLevel());
	   model.addAttribute("schools", schools);

	   return "registration.jsp";
   }

   @RequestMapping(value = "/students/{studentId}/school", method = RequestMethod.POST, params="verb=register")
   public String register(@PathVariable long studentId, @RequestParam long schoolId) {

	   studentDao.register(studentId, schoolId);
	   return "redirect:/students/" + studentId + "/detail.html";
   }

   @RequestMapping(value = "/deletestudent")
   public String deleteStudent(HttpServletRequest request) {
	   String in = request.getParameter("id");
	   try {
		   long id = Long.parseLong(in);
		   studentDao.delete(id);
	   }
	   catch(NumberFormatException nfe) {
		   logger.severe("could not interpret student id: " + in);
	   }
	   return "redirect:/students.html";
   }
   
}
