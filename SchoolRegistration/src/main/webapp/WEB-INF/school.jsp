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
<jsp:useBean id="currentPageIndex" type="java.lang.Integer" scope="request" />
<jsp:useBean id="maxPageIndex" type="java.lang.Integer" scope="request" />
<jsp:useBean id="pageSort" type="java.lang.String" scope="request" />
<!DOCTYPE html>
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
<div id="pagination" data-current-page="<%=currentPageIndex %>" data-max-page="<%=maxPageIndex %>" data-current-sort="<%=pageSort%>">
	<span id="prevPage">&lt;</span><span>Page <%=currentPageIndex+1 %> of <%=maxPageIndex+1 %></span><span id="nextPage">&gt;</span>
</div>
<script type="text/javascript" src="/scripts/table.js"></script>
</body>
</html>