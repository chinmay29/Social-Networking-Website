<%@ include file="../header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Create Event</title>
</head>
<body>

<h2>Created Event Information</h2>
<form:form method="POST" action="/connect/createEvent" commandName="event">
   <table>
    <tr>
        <td><form:label path="e_name">Name</form:label></td>
        <td><form:input path="e_name" /></td>
    </tr>
    <tr>
        <td><form:label path="e_location">Location</form:label></td>
        <td><form:input path="e_location" /></td>
    </tr>
    <tr>
        <td><form:label path="e_description">Description</form:label></td>
        <td><form:input path="e_description" /></td>
    </tr>
    <tr>
        <td><form:label path="e_date">Date</form:label></td>
        <td><form:input path="e_date" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>
<%@ include file="../footer.jsp" %>