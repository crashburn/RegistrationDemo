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
	<title>Student Details</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<a class="button" href="/students.html">Back to Students List</a>
	<hr/>
	<h1>Student Profile</h1>
		First Name: ${student.firstName}<br/>
		Last Name: ${student.lastName}<br/>
		Sex: ${student.sex}<br/>
		Birth Date: ${student.formattedBirthdate}<br/>
	
		Street: ${student.address.street}<br/>
		City: ${student.address.city}<br/>
		State: ${student.address.state}<br/>
		Zip: ${student.address.zip}<br/>
	
		Phone Number: (${student.phoneNumber.areaCode}) 
					${student.phoneNumber.exchange}-${student.phoneNumber.subscriberNumber}<br/>
	
		Grade Level: ${student.gradeLevel.displayValue}<br/>
	   	School: ${ student.school!=null ? student.school.name : "Not registered" }
		
		<hr/>
		<div><a class="button" href="/students/${student.id}/school.html">Register for School</a></div>
</body>
</html>