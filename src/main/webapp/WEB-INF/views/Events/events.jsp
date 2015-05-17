<%@ include file="../header.jsp"%>
	<a href="http://localhost:8080/connect/search" class="btn pull-right"><i
		class="icon-search"></i> &nbsp;&nbsp;Search Event</a>

<a href="http://localhost:8080/connect/create" class="btn pull-right"><i
	class="icon-plus"></i> &nbsp;&nbsp;Create Event</a>
<div>
	<c:forEach items="${events}" var="row">
		Event Name  : <a href="<c:url value="/viewEvent/${row.e_id}" />">${row.e_name}
		</a>
		<br />
	</c:forEach>
</div>


<%@ include file="../footer.jsp"%>