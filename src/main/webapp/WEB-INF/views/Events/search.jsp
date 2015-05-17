<%@ include file="../header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Search Event</title>
</head>
<body>

<h2>Enter the Name of the Event to be searched</h2>
<form:form method="POST" action="/connect/searchEvent" commandName="event">
   <table>
    <tr>
        <td><form:label path="e_name">Name: </form:label></td>
        <td><form:input path="e_name" /></td>
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