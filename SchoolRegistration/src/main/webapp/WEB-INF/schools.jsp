<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

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
		Name: <input type="text" name="name" /> <br/>
		Street: <input type="text" name="street" />
		City: <input type="text" name="city" /> 
		State: <input type="text" name="state" /> 
		Zip: <input type="text" name="zip" /> <br/> 
		Min Grade Level: <select name="minGradeLevel">
			<% for(GradeLevel gl : GradeLevel.values()) { %>
			<option value="<%=gl.name() %>"><%=gl.getDisplayValue() %></option>
			<% } %>
		</select>
		Max Grade Level: <select name="maxGradeLevel">
			<% for(GradeLevel gl : GradeLevel.values()) { %>
			<option value="<%=gl.name() %>"><%=gl.getDisplayValue() %></option>
			<% } %>
		</select>
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