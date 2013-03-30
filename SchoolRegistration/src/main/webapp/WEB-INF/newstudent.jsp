<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Add a Student</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<h1>Add a Student</h1>
	<form:form id="studentForm" method="POST" action="/students.html" modelAttribute="student">
		<div>
			First Name: <form:input path="firstName" /> 
			Last Name: <form:input path="lastName" />
		</div>
		<div>
			Sex: <form:select path="sex"> 
				<form:options />
			</form:select>
			Grade Level: <form:select path="gradeLevel"> 
				<form:options itemLabel="displayValue"/>
			</form:select> 
			Birth Date: (MM/dd/yyyy)<form:input path="birthdate" /> 
		</div>
		<div>
			Street: <form:input path="address.street" />
			City: <form:input path="address.city" /> 
			State: <form:input path="address.state" /> 
			Zip: <form:input path="address.zip" /> 
		</div>
		<div>
			Phone Number: <form:input path="phoneNumber.areaCode" /> 
			<form:input path="phoneNumber.exchange" /> 
			<form:input path="phoneNumber.subscriberNumber" />
		</div> 
	</form:form>
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