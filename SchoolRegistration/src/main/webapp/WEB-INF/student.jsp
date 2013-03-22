<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="student" type="reg.Student" scope="request" />
<jsp:useBean id="schoolDao" type="reg.SchoolDao" scope="request" />
<!DOCTYPE html>
<html>
<head>
<title>Student Details</title>
</head>
<body>
<a href="students.html">Back to Students List</a>
<br/>
<% if(student != null) { %>
	First Name: <%=student.getFirstName()%><br/>
	Last Name: <%=student.getLastName()%><br/>
	Sex: <%=student.getSex()%><br/>
	Birth Date: <%=student.getFormattedBirthdate("MM/dd/yyyy")%><br/>

	Street: <%=student.getAddress().getStreet()%><br/>
	City: <%=student.getAddress().getCity()%><br/>
	State: <%=student.getAddress().getState()%><br/>
	Zip: <%=student.getAddress().getZip()%><br/>

	Phone Number: (<%=student.getPhoneNumber().getAreaCode()%>) 
				<%=student.getPhoneNumber().getExchange()%>-<%=student.getPhoneNumber().getSubscriberNumber()%><br/>

	Grade Level: <%=student.getGradeLevel().getDisplayValue()%><br/>

	<% School school = student.getSchool(); %>
   	School: <%=((school!=null) ? school.getName() : "Not registered") %>
	<form method="POST" action="register.html">
		<input type="hidden" name="studentId" value="<%=student.getId() %>" />
		<%
   		for (School choice : schoolDao.getAllSchools()) {
		%>
			<input type="radio" name="choiceId" value="<%=choice.getId()%>" /><%=choice.getName()%>
		<%
   		}
		%>
		<input type="submit" value="Add" />
	</form>
<% } %>

</body>
</html>