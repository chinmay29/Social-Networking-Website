<%@ include file="../header.jsp" %>
<table class="table table-striped table-bordered">
<h3><c:out value="${MSG}"></c:out></h3>
<tr><th>Friend Requests </th><th></th><th></th></tr>

<c:forEach var="row" items="${getfriendReq}">
   
<tr><td> ${row.name}</td>
<form method="post" action="${pageContext.request.contextPath}/acceptreq" >
<input type="hidden" name="id" value=" ${row.id }"/>
<td><input type="submit" value="Accept" class="btn btn-success"> </td>
</form>
<form method="post" action="${pageContext.request.contextPath}/rejectreq" >
<input type="hidden" name="id" value="${row.id }"/>
<td><input type="submit" value="Reject" class="btn btn-danger"></td>
</tr>
</form>
</c:forEach>
</table>

<%@ include file="../footer.jsp" %>