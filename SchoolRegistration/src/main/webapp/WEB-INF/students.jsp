<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="studentDao" type="reg.StudentDao" scope="request" />
<!DOCTYPE html>
<html>
<head>
<title>Students</title>
</head>
<body>
	<form method="POST" action="newstudent.html">
		First Name: <input type="text" name="firstName" /> 
		Last Name: <input type="text" name="lastName" /> <br/>
		Sex: <select name="sex"> 
			<% for(Sex s : Sex.values()) { %>
			<option value="<%=s.name() %>"><%=s %></option>
			<% } %>
		</select>
		Grade Level: <select name="gradeLevel"> 
			<% for(GradeLevel gl : GradeLevel.values()) { %>
			<option value="<%=gl.name() %>"><%=gl.getDisplayValue() %></option>
			<% } %>
		</select> 
		Birth Date: (MM)<input type="text" name="birthMonth" /> 
		(DD)<input type="text" name="birthDay" /> 
		(YYYY)<input type="text" name="birthYear" /> <br/> 
		Street: <input type="text" name="street" />
		City: <input type="text" name="city" /> 
		State: <input type="text" name="state" /> 
		Zip: <input type="text" name="zip" /> <br/> 
		Phone Number: <input type="text" name="areaCode" /> 
		<input type="text" name="exchange" /> 
		<input type="text" name="subscriberNumber" /> <br/> 
		
		<input type="submit" value="Add" />
	</form>
<hr>
<ol>
<%
   for (Student student : studentDao.getAllStudents()) {
%>
<li><%=student.getLastName()%>,<%=student.getFirstName()%> <a href="viewstudent.html?id=<%=student.getId()%>">View</a> <a href="deletestudent.html?id=<%=student.getId()%>">delete</a></li>
<%
   }
%>
</ol>
<hr>
</body>
</html>