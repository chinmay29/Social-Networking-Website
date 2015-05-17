<%@ include file="../header.jsp"%>
<a href="http://localhost:8080/connect/search" class="btn pull-right"><i class="icon-search"></i> &nbsp;&nbsp;Search Event</a>

<h2>Search Results</h2>
<div>
	<c:forEach items="${events}" var="row">
		Event Name  : <a href="<c:url value="/viewEventUser/${row.e_id}" />" >${row.e_name} </a><br />
	</c:forEach>	
</div>


<%@ include file="../footer.jsp" %>