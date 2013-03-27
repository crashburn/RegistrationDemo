<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="students" type="java.util.List<Student>" scope="request" />
<jsp:useBean id="tableState" type="reg.TableState" scope="request" />
<!DOCTYPE html>
<html>
<head>
	<title>Students</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<h1>Students</h1>
	<div class="tablewrapper">
		<table>
		<tr id="headerRow">
		<th id="lastName">Last Name</th>
		<th id="firstName">First Name</th>
		<th id="sex">Sex</th>
		<th id="school">School Name</th>
		<th id="gradeLevel">Grade Level</th>
		</tr>
		<% for (Student student : students) { %>
			<tr>
			<td><a href="/students/<%=student.getId()%>/detail.html"><%=student.getLastName()%></a></td>
			<td><%=student.getFirstName()%></td>
			<td><%=student.getSex()%></td>
			<td><%=((student.getSchool() != null) ? student.getSchool().getName() : "Not registered") %></td>
			<td><%=student.getGradeLevel().getDisplayValue()%></td>
			</tr>
		<% } %>
		</table>
		<div id="pagination" data-current-page="<%=tableState.getPageIndex() %>" 
							 data-max-page="<%=tableState.getMaxPageIndex() %>" 
							 data-current-sort="<%=tableState.getSortBy()%>">
			<span id="prevPage" class="button">&lt;</span>
			<span>Page <%=tableState.getPageNumber() %> of <%=tableState.getMaxPageNumber() %></span>
			<span id="nextPage" class="button">&gt;</span>
		</div>
	</div>
	<hr>
	<div><a class="button" href="/students/new.html">Add a Student</a></div>
	<script type="text/javascript" src="/scripts/table.js"></script>
</body>
</html>