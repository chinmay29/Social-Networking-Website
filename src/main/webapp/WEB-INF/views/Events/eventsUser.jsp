<%@ include file="../header.jsp"%>
<a href="http://localhost:8080/connect/search" class="btn pull-right"><i class="icon-search"></i> &nbsp;&nbsp;Search Event</a> <br /><br /> <br />

<a href="http://localhost:8080/connect/create" class="btn pull-right"><i class="icon-plus"></i> &nbsp;&nbsp;Create Event</a> <br /><br />
<div style="margin-top: -100px;">
	<c:forEach items="${events}" var="row">
		Event Name  : <a href="<c:url value="/viewEventUser/${row.e_id}" />" >${row.e_name} </a><br />
	</c:forEach>
</div>


<%@ include file="../footer.jsp" %>