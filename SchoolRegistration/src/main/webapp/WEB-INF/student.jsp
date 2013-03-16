<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="student" type="reg.Student" scope="request" />
<jsp:useBean id="schoolDao" type="reg.SchoolDao" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Student Details</title>
</head>
<body>
<a href="students.html">Back to Students List</a>
<br/>
<% if(student != null) { %>
	First Name: <%=student.getFirstName()%><br/>
	Last Name: <%=student.getFirstName()%><br/>
	<% School school = student.getSchool(); %>
   	School: <%=((school!=null) ? school.getName() : "Not registered") %>
	<form method="POST" action="register.html">
		<input type="hidden" name="studentId" value="<%=student.getId() %>" />
		<%
   		for (School choice : schoolDao.getAllSchools()) {
		%>
			<input type="radio" name="choiceId" value="<%=choice.getId()%>"><%=choice.getName()%></input>
		<%
   		}
		%>
		<input type="submit" value="Add" />
	</form>
<% } %>

</body>
</html>