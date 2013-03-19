<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="school" type="reg.School" scope="request" />
<jsp:useBean id="students" type="java.util.List<Student>" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>School Details</title>
</head>
<body>
<a href="/schools.html">Back to Schools List</a>
<br/>
<%=school%> 
<hr/>
Students:<br/>
<table>
<tr id="headerRow">
<th id="lastName">Last Name</th>
<th id="firstName">First Name</th>
<th id="sex">Sex</th>
<th id="age">Age</th>
<th id="gradeLevel">Grade Level</th>
</tr>
<% for (Student student : students) { %>
	<tr>
	<td><%=student.getLastName()%></td>
	<td><%=student.getFirstName()%></td>
	<td><%=student.getSex()%></td>
	<td><%=student.getAge()%></td>
	<td><%=student.getGradeLevel().getDisplayValue()%></td>
	</tr>
<% } %>
</table>
<script>
	//Function to change the content of t2
	function tableSort(sortBy) {
		window.location.search = 'sortBy=' + sortBy;
	}
	
	function listenForClicks() {
		var cols = document.getElementById("headerRow").getElementsByTagName("th");
		for(var i=0, size=cols.length; i < size; i++) {
			cols[i].addEventListener("click", function() { tableSort(this.id); }, false);
		}
	}
	
	listenForClicks();
</script>
</body>
</html>