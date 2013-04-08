/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package reg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
   
   @InitBinder
   protected void initBinder(WebDataBinder binder) {
       SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
       dateFormat.setLenient(false);
       binder.registerCustomEditor(Date.class, new CustomDateEditor(
               dateFormat, false));
   }
   
   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public String goHome() {
	   return "home.jsp";
   }
   
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
   public String addSchool(@Valid School school, BindingResult result) {

	   String view = "newschool.jsp";
	   if(!result.hasErrors()) {
		   // Persist the new school
		   schoolDao.persist(school);
		   view = "redirect:/schools.html";
	   }
	   return view;
   }

   @RequestMapping(value = "/schools/new", method = RequestMethod.GET)
   public String initNewSchool(Model model) {
	   model.addAttribute(new School());
	   model.addAttribute("backURI", "/schools.html");
	   model.addAttribute("submitURI", "/schools.html");
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

   @RequestMapping(value = "/schools/{schoolId}/form", method = RequestMethod.GET)
   public String getSchoolForm(@PathVariable long schoolId, Model model) {
	   // Populate the school
	   School school = schoolDao.retrieve(schoolId);
	   model.addAttribute(school);
	   model.addAttribute("backURI", "/schools/" + schoolId + "/detail.html");
	   model.addAttribute("submitURI", "/schools/" + schoolId + "/form.html");
	   return "editschool.jsp";
   }

   @RequestMapping(value = "/schools/{schoolId}/form", method = RequestMethod.POST)
   public String editSchool(@PathVariable long schoolId, @Valid School school, BindingResult result) {
	   System.out.println("edit id[" + schoolId + "] " + school);
	   String view = "editschool.jsp";
	   school.setId(schoolId);
	   if(!result.hasErrors()) {
		   // Persist the new school
		   schoolDao.update(school);
		   view = "redirect:/schools/" + schoolId + "/detail.html";
	   }
	   return view;
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
   public String addStudent(@Valid Student student, BindingResult result) {

	   String view = "newstudent.jsp";
	   if(!result.hasErrors()) {
		   // Persist the new student
		   studentDao.persist(student);
		   view = "redirect:/students/" + student.getId() + "/school.html";
	   }
	   
	   return view;
   }

   @RequestMapping(value = "/students/new", method = RequestMethod.GET)
   public String initNewStudent(Model model) {
	   model.addAttribute(new Student());
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
