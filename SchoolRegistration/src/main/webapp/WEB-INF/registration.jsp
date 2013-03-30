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
	<title>Register for a School</title>
	<link rel="stylesheet" type="text/css" href="/styles/theme.css">
</head>
<body>
	<a class="button" href="/students/${studentId}/detail.html">Back to Student Profile</a>
	<hr/>
	<h1>Matching Schools by Zip and Grade Level</h1>
	<form method="POST" action="/students/${studentId}/school.html">
		<input type="hidden" name="verb" value="register" />
		<c:forEach var="choice" items="${schools}">
			<input type="radio" name="schoolId" value="${choice.id}" 
				<c:if test="${currentSchoolId == choice.id}" >checked</c:if> />${choice.name}
		</c:forEach>
		<input type="submit" value="Register" />
	</form>
</body>
</html>