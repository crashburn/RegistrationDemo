<%-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. --%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="theForm" method="POST" action="${submitURI}" modelAttribute="school">
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
