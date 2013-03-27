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
	<title>Add a Student</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<h1>Add a Student</h1>
	<form id="studentForm" method="POST" action="/students.html">
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
	</form>
	<hr>
	<div id="buttons">
		<span id="btnAdd" class="button">Add</span>
		<span id="btnCancel" class="button">Cancel</span>
	</div>
	<script>
		function listenForButtonClicks() {
			var btnAdd = document.getElementById("btnAdd");
			var studentForm = document.getElementById("studentForm");
			btnAdd.addEventListener("click", function() { studentForm.submit(); }, false);
			var btnCancel = document.getElementById("btnCancel");
			btnCancel.addEventListener("click", function() { window.location = "/students.html"; }, false);
		}
		listenForButtonClicks();
	</script>
</body>
</html>