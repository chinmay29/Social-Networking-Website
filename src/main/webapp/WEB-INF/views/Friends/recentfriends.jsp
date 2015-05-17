<%@ include file="../header.jsp" %>
<h2>Recently Added Friends: </h2>
<table class="table table-striped table-bordered">
<tr><th>Name</th></tr>
<c:forEach var="row" items="${recentfriend}">
    
   <tr><td> ${row.name}</td></tr>
</c:forEach>
</table>
<%@ include file="../footer.jsp" %>