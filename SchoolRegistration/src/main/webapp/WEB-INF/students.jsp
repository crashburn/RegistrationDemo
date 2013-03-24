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
</head>
<body>
	<form method="POST" action="/students.html">
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
<h1>Students</h1>
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
	<span id="prevPage">&lt;</span>
	<span>Page <%=tableState.getPageNumber() %> of <%=tableState.getMaxPageNumber() %></span>
	<span id="nextPage">&gt;</span>
</div>
<hr>
<script type="text/javascript" src="/scripts/table.js"></script>
</body>
</html>