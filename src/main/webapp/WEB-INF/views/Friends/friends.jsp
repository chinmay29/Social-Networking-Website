<%@ include file="../header.jsp" %>
<div class="page-header">
<h1>Friend List </h1>
<table>
<tr>
    <th>Name</th>
</tr>
    
<c:forEach var="row" items="${friends}">
  	<tr>
    <td>${row.name}</td>
    </tr>
    
</c:forEach>
</table>
</div>
<%@ include file="../footer.jsp" %>