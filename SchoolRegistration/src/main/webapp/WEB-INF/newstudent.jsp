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
	<%@include file="header.jsp"%>
	<section>
		<h2>Add a Student</h2>
		<form:form id="studentForm" method="POST" action="/students.html" modelAttribute="student">
			<div>
				First Name: <form:input path="firstName" size="20" maxlength="20" /> <form:errors path="firstName" cssClass="error" />
				Last Name: <form:input path="lastName" size="20" maxlength="20" /> <form:errors path="lastName" cssClass="error" />
			</div>
			<div>
				Sex: <form:select path="sex"> 
					<form:options />
				</form:select>
				Grade Level: <form:select path="gradeLevel"> 
					<form:options itemLabel="displayValue"/>
				</form:select> 
			</div>
			<div>
				Birth Date: (MM/dd/yyyy)<form:input path="birthdate" /> <form:errors path="birthdate" cssClass="error" />
			</div>
			<div>
				Street: <form:input path="address.street" size="50" maxlength="50" /> <form:errors path="address.street" cssClass="error" />
			</div>
			<div>
				City: <form:input path="address.city" size="20" maxlength="20" /> <form:errors path="address.city" cssClass="error" />
				State: <form:input path="address.state" size="2" maxlength="2" /> <form:errors path="address.state" cssClass="error" />
				Zip: <form:input path="address.zip" size="5" maxlength="5" /> <form:errors path="address.zip" cssClass="error" />
			</div>
			<div>
				Phone Number: 
				(<form:input path="phoneNumber.areaCode" size="3" maxlength="3" />) <form:errors path="phoneNumber.areaCode" cssClass="error" />
				<form:input path="phoneNumber.exchange" size="3" maxlength="3" /> <form:errors path="phoneNumber.exchange" cssClass="error" />-
				<form:input path="phoneNumber.subscriberNumber" size="4" maxlength="4" /> <form:errors path="phoneNumber.subscriberNumber" cssClass="error" />
			</div> 
		</form:form>
		<hr>
		<div id="buttons">
			<span id="btnAdd" class="button">Add</span>
			<span id="btnCancel" class="button">Cancel</span>
		</div>
	</section>
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