<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="reg.*"%>
<jsp:useBean id="studentId" type="java.lang.Long" scope="request" />
<jsp:useBean id="currentSchoolId" type="java.lang.Long" scope="request" />
<jsp:useBean id="schools" type="java.util.List<School>" scope="request" />
<!DOCTYPE html>
<html>
<head>
<title>Register for a School</title>
</head>
<body>
<a href="/students/<%=studentId%>/detail.html">Back to Student Profile</a>
<hr/>
	<h1>Matching Schools by Zip and Grade Level</h1>
	<form method="POST" action="/students/<%=studentId%>/school.html">
		<input type="hidden" name="verb" value="register" />
		<%
   		for (School choice : schools) {
		%>
			<input type="radio" name="schoolId" value="<%=choice.getId()%>" 
				<% if(currentSchoolId.longValue() == choice.getId()) out.write("checked"); %> /><%=choice.getName()%>
		<%
   		}
		%>
		<input type="submit" value="Register" />
	</form>
</body>
</html>