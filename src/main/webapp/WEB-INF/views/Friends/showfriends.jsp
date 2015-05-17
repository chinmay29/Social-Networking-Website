<%@ include file="../header.jsp" %>
<div class="page-header">
<h3><c:out value="${MSG}"></c:out></h3>

<h2>Friend List </h2>
<table class="table table-striped table-bordered">
<tr>
    <th>Name :</th>
	<th></th>
	<th></th>
</tr>
    
<c:forEach var="row" items="${friends}">
  	<tr>
    <td>${row.name}</td>
 	<form method="post" action="${pageContext.request.contextPath}/mutualfriend" >
		<input type="hidden" name="id" value=" ${row.id }"/>
		<td><input type="submit" value="See Mutual Friends" class="btn btn-primary"></td>
		</form>    	
 	<form method="post" action="${pageContext.request.contextPath}/removefriend" >
		<input type="hidden" name="id" value=" ${row.id }"/>
		<td><input type="submit" value="Remove" class="btn btn-danger"> </td>
		</form>

 	</tr>   
</c:forEach>
</table>
</div>
<%@ include file="../footer.jsp" %>


