<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Students</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<section>
		<h2>Students</h2>
		<div class="tablewrapper">
			<table>
			<tr id="headerRow">
			<th id="lastName">Last Name</th>
			<th id="firstName">First Name</th>
			<th id="sex">Sex</th>
			<th id="school">School Name</th>
			<th id="gradeLevel">Grade Level</th>
			</tr>
			<c:forEach var="student" items="${students}">
				<tr>
				<td><a href="/students/${student.id}/detail.html">${student.lastName}</a></td>
				<td>${student.firstName}</td>
				<td>${student.sex}</td>
				<td>${(student.school != null) ? student.school.name : "Not registered"}</td>
				<td>${student.gradeLevel.displayValue}</td>
				</tr>
			</c:forEach>
			</table>
			<div id="pagination" data-current-page="${tableState.pageIndex}" 
								 data-max-page="${tableState.maxPageIndex}" 
								 data-current-sort="${tableState.sortBy}">
				<span id="prevPage" class="button">&lt;</span>
				<span>Page ${tableState.pageNumber} of ${tableState.maxPageNumber}</span>
				<span id="nextPage" class="button">&gt;</span>
			</div>
		</div>
		<hr>
		<div><a class="button" href="/students/new.html">Add a Student</a></div>
	</section>
	<script type="text/javascript" src="/scripts/table.js"></script>
</body>
</html>