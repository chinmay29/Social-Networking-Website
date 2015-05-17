<%@ include file="../header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Event Creation</title>
</head>
<body>

<h2>Created Event Information</h2>
   <table>
    <tr>
        <td>Name : </td>
        <td>${e_name} </td>
    </tr> 
    <tr>
        <td>Location : </td>
        <td>${e_location}</td>
    </tr>
    <tr>
        <td>Date : </td>
        <td>${e_date}</td>
    </tr>  
    <tr>
        <td>Description : </td>
        <td>${e_description}</td>
    </tr>
    <tr>
        <td>Total Participants : </td>
        <td>${e_joined}</td>
    </tr>
    
</table>  
</body>
</html>
<%@ include file="../footer.jsp" %>