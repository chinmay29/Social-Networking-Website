<%@ include file="../header.jsp" %>
<h3><c:out value="${MSG}"></c:out></h3>
<p>
<h3>Mutual Friends: </h3>
</p>
<table class="table table-striped table-bordered">
  <tr><th>Name</th></tr>
<c:forEach var="row" items="${mutual}">
 
   <tr><td> ${row.name}</td></tr>
  
</c:forEach>
</table>
<%@ include file="../footer.jsp" %>