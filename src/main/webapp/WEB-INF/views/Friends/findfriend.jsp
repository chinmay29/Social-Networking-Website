<%@ include file="../header.jsp" %>
<p>Search result for friends:  </p>
<table class="table table-striped table-bordered">
  <tr><th>Name</th><th>Mutual Friends</th><th></th><th></th></tr>
<c:forEach var="row" items="${findfriends}">
  
    
    <tr>
    	<td> ${row.name}</td>
    	<td> ${row.id2 }   	</td>
    	
		<form method="post" action="${pageContext.request.contextPath}/mutualfriend" >
		<input type="hidden" name="id" value=" ${row.id }"/>
		<td><input type="submit" value="See Mutual Friends" class="btn btn-primary"></td>
		</form>
    	<form method="post" action="${pageContext.request.contextPath}/removefriend" >
		<input type="hidden" name="id" value=" ${row.id }"/>
		<td><input type="submit" value="Remove" class="btn btn-danger"> </td>
		</form>
    </c:forEach>
    </table>

<%@ include file="../footer.jsp" %>