<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="schools" type="java.util.List<School>" scope="request" />
<jsp:useBean id="currentPageIndex" type="java.lang.Integer" scope="request" />
<jsp:useBean id="maxPageIndex" type="java.lang.Integer" scope="request" />
<jsp:useBean id="pageSort" type="java.lang.String" scope="request" />
<!DOCTYPE html>
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
Schools:<br/>
<table>
<tr id="headerRow">
<th id="name">Name</th>
<th id="city">City</th>
<th id="state">State</th>
<th id="zip">Zip</th>
<th>Remove</th>
</tr>
<% for (School school : schools) { %>
	<tr>
	<td><a href="viewschool/<%=school.getId()%>/detail.html"><%=school.getName()%></a></td>
	<td><%=school.getCity()%></td>
	<td><%=school.getState()%></td>
	<td><%=school.getZip()%></td>
	<td><a href="deleteschool.html?id=<%=school.getId()%>">delete</a></td>
	</tr>
<% } %>
</table>
<div id="pagination" data-current-page="<%=currentPageIndex %>" data-max-page="<%=maxPageIndex %>", data-current-sort="<%=pageSort%>">
	<span id="prevPage">&lt;</span><span>Page <%=currentPageIndex+1 %> of <%=maxPageIndex+1 %></span><span id="nextPage">&gt;</span>
</div>
<hr>
<script type="text/javascript" src="/scripts/table.js"></script>
</body>
</html>