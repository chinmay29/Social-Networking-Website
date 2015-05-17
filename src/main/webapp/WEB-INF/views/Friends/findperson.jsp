<%@ include file="../header.jsp" %>
<h2>Search result: </h2>
<h3><c:out value="${MSG}"></c:out></h3>

<table class="table table-striped table-bordered">
<tr><th>Name</th><th>Connections/Recommendations</th><th>Mutual Friends</th><th>Status</th><th></th></tr>
    
<c:forEach var="row" items="${findpersons}">
        
    <tr>
    	<td> ${row.name}</td>
       	<td>${row.sb }</td>
    	<td>${row.id2 } </td>
    	<td>${row.status} </td>
    		<form method="post" action="${pageContext.request.contextPath}/addfriend" >
		<input type="hidden" name="id" value=" ${row.id }"/>
		<td><input type="submit" value="Send Friend Request" class="btn btn-info"> </td>
		</form>
    	
    </tr>
   
    </c:forEach>
</table>
<%@ include file="../footer.jsp" %>