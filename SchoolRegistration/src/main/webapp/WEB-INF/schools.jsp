<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="schoolDao" type="reg.SchoolDao" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Schools</title>
</head>
<body>
	<form method="POST" action="newschool.html">
		Name: <input type="text" name="name" /> 
		City: <input type="text" name="city" /> 
		State: <input type="text" name="state" /> 
		<input type="submit" value="Add" />
	</form>
<hr>
<ol>
<%
   for (School school : schoolDao.getAllSchools()) {
%>
<li><%=school.getName()%> <a href="viewschool.html?id=<%=school.getId()%>">View</a> <a href="deleteschool.html?id=<%=school.getId()%>">delete</a></li>
<%
   }
%>
</ol>
<hr>
</body>
</html>