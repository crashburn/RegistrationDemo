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
<jsp:useBean id="tableState" type="reg.TableState" scope="request" />
<!DOCTYPE html>
<html>
<head>
<title>School Details</title>
</head>
<body>
<a class="button" href="/schools.html">Back to Schools List</a>
<br/>
<h1>School Details</h1>
<%=school%> 
<form id="schoolForm" method="POST" action="/schools/<%=school.getId()%>/detail.html">
	<input type="hidden" id="verb" name="verb" value="" />
	<div id="buttons">
		<span id="btnEdit" class="button">Edit</span>
		<span id="btnDelete" class="button">Delete</span>
	</div>
</form>
<hr/>
<h2>Students</h2>
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
<div id="pagination" data-current-page="<%=tableState.getPageIndex() %>" 
					 data-max-page="<%=tableState.getMaxPageIndex() %>" 
					 data-current-sort="<%=tableState.getSortBy()%>">
	<span id="prevPage">&lt;</span>
	<span>Page <%=tableState.getPageNumber() %> of <%=tableState.getMaxPageNumber() %></span>
	<span id="nextPage">&gt;</span>
</div>
<script type="text/javascript" src="/scripts/table.js"></script>
<script>
function submitForm(verbValue) {
	var schoolForm = document.getElementById("schoolForm");
	var verbElement = document.getElementById("verb");
	verbElement.setAttribute("value", verbValue); 
	schoolForm.submit();
}
function listenForButtonClicks() {
	var btnEdit = document.getElementById("btnEdit");
	btnEdit.addEventListener("click", function() { submitForm("edit"); }, false);
	var btnDelete = document.getElementById("btnDelete");
	btnDelete.addEventListener("click", function() { submitForm("delete"); }, false);
}

listenForButtonClicks();
</script>
</body>
</html>