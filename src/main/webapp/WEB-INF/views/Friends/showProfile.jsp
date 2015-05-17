<%@ include file="../header.jsp"%>
<table>
<c:forEach var="row" items="${profile}">
  
    
    <tr>
    	<td> ${row.firstName}</td>
    	<td> ${row.lastName}   	</td>
    	<td>${row.lastName}</td>
    	</tr>
    </c:forEach>
    </table>

<%@ include file="../footer.jsp"%>