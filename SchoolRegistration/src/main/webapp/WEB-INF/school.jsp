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
	<title>School Details</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<section>
		<a class="button" href="/schools.html">Back to Schools List</a>
		<h2>${school.name}</h2>
		<div>${school.address.street}</div>
		<div>${school.address.city}, ${school.address.state} ${school.address.zip}</div>
		<div>Grade Level ${school.minGradeLevel.displayValue} - ${school.maxGradeLevel.displayValue}</div>
		<div id="buttons">
			<a class="button" href="/schools/${school.id}/form.html">Edit</a>
			<span id="btnDelete" class="button">Delete</span>
		</div>
		<hr/>
		<h3>Enrolled Students</h3>
		<div class="tablewrapper">
			<table>
			<tr id="headerRow">
			<th id="lastName">Last Name</th>
			<th id="firstName">First Name</th>
			<th id="sex">Sex</th>
			<th id="age">Age</th>
			<th id="gradeLevel">Grade Level</th>
			</tr>
			<c:forEach var="student" items="${students}">
				<tr>
				<td>${student.lastName}</td>
				<td>${student.firstName}</td>
				<td>${student.sex}</td>
				<td>${student.age}</td>
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
		<form id="schoolForm" method="POST" action="/schools/${school.id}/detail.html">
			<input type="hidden" id="verb" name="verb" value="" />
		</form>
	</section>
	<script type="text/javascript" src="/scripts/table.js"></script>
	<script>
		function submitForm(verbValue) {
			if(confirm("Are you sure you want to delete this school?")) {
				var schoolForm = document.getElementById("schoolForm");
				var verbElement = document.getElementById("verb");
				verbElement.setAttribute("value", verbValue); 
				schoolForm.submit();
			}
		}
		function listenForButtonClicks() {
			var btnDelete = document.getElementById("btnDelete");
			btnDelete.addEventListener("click", function() { submitForm("delete"); }, false);
		}
		listenForButtonClicks();
	</script>
</body>
</html>