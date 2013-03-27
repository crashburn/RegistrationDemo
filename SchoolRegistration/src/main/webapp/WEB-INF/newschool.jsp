<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add a School</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<h1>Add a School</h1>
	<form id="schoolForm" method="POST" action="/schools.html">
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
	</form>
	<hr>
	<div id="buttons">
		<span id="btnAdd" class="button">Add</span>
		<span id="btnCancel" class="button">Cancel</span>
	</div>
	<script>
		function listenForButtonClicks() {
			var btnAdd = document.getElementById("btnAdd");
			var schoolForm = document.getElementById("schoolForm");
			btnAdd.addEventListener("click", function() { schoolForm.submit(); }, false);
			var btnCancel = document.getElementById("btnCancel");
			btnCancel.addEventListener("click", function() { window.location = "/schools.html"; }, false);
		}
		listenForButtonClicks();
	</script>
</body>
</html>