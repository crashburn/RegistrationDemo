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
	<title>Schools</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<section>
		<h2>Schools</h2>
		<div class="tablewrapper">
			<table>
			<tr id="headerRow">
			<th id="name">Name</th>
			<th id="city">City</th>
			<th id="state">State</th>
			<th id="zip">Zip</th>
			</tr>
			<c:forEach var="school" items="${schools}">
				<tr>
				<td><a href="/schools/${school.id}/detail.html">${school.name}</a></td>
				<td>${school.address.city}</td>
				<td>${school.address.state}</td>
				<td>${school.address.zip}</td>
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
		<div><a class="button" href="/schools/new.html">Add a School</a></div>
	</section>
	<script type="text/javascript" src="/scripts/table.js"></script>
</body>
</html>