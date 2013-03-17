<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="studentDao" type="reg.StudentDao" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Students</title>
</head>
<body>
	<form method="POST" action="newstudent.html">
		First Name: <input type="text" name="firstName" /> 
		Last Name: <input type="text" name="lastName" /> 
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