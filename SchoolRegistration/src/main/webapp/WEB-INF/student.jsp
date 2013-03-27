<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="student" type="reg.Student" scope="request" />
<jsp:useBean id="schools" type="java.util.List<School>" scope="request" />
<!DOCTYPE html>
<html>
<head>
	<title>Student Details</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<a class="button" href="/students.html">Back to Students List</a>
	<hr/>
	<h1>Student Profile</h1>
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
		
		<hr/>
		<div><a class="button" href="/students/<%=student.getId() %>/school.html">Register for School</a></div>
	<% } %>
</body>
</html>