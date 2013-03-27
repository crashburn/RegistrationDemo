<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="schools" type="java.util.List<School>" scope="request" />
<jsp:useBean id="tableState" type="reg.TableState" scope="request" />
<!DOCTYPE html>
<html>
<head>
	<title>Schools</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<h1>Schools</h1>
	<div class="tablewrapper">
		<table>
		<tr id="headerRow">
		<th id="name">Name</th>
		<th id="city">City</th>
		<th id="state">State</th>
		<th id="zip">Zip</th>
		</tr>
		<% for (School school : schools) { %>
			<tr>
			<td><a href="/schools/<%=school.getId()%>/detail.html"><%=school.getName()%></a></td>
			<td><%=school.getCity()%></td>
			<td><%=school.getState()%></td>
			<td><%=school.getZip()%></td>
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
	<div><a class="button" href="/schools/new.html">Add a School</a></div>
	<script type="text/javascript" src="/scripts/table.js"></script>
</body>
</html>