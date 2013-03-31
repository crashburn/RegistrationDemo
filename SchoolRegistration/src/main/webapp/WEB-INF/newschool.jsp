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
	<title>Add a School</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<h1>Add a School</h1>
	<form:form id="schoolForm" method="POST" action="/schools.html" modelAttribute="school">
		<div>
			Name: 
			<form:input path="name" size="30" maxlength="30" />
			<form:errors path="name" cssClass="error" />
		</div>
		<div>
			Street: 
			<form:input path="address.street" size="50" maxlength="50" />
			<form:errors path="address.street" cssClass="error" />
		</div>
		<div>
			City: 
			<form:input path="address.city" size="20" maxlength="20" /> 
			<form:errors path="address.city" cssClass="error" />
		</div>
		<div>
			State: 
			<form:input path="address.state" size="2" maxlength="2" /> 
			<form:errors path="address.state" cssClass="error" />
		</div>
		<div>
			Zip: 
			<form:input path="address.zip" size="5" maxlength="5" />
			<form:errors path="address.zip" cssClass="error" />
		</div>
		<div>
			Min Grade Level: <form:select path="minGradeLevel" >
				<form:options itemLabel="displayValue"/>
			</form:select>
			Max Grade Level: <form:select path="maxGradeLevel" >
				<form:options itemLabel="displayValue"/>
			</form:select>
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
			var schoolForm = document.getElementById("schoolForm");
			btnAdd.addEventListener("click", function() { schoolForm.submit(); }, false);
			var btnCancel = document.getElementById("btnCancel");
			btnCancel.addEventListener("click", function() { window.location = "/schools.html"; }, false);
		}
		listenForButtonClicks();
	</script>
</body>
</html>