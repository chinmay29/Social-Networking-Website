<%@ include file="../header.jsp"%>

<a href="http://localhost:8080/connect/search" class="btn pull-right"><i class="icon-search"></i> &nbsp;&nbsp;Search Event</a>

	<div>
		Event Name 		   : ${events.e_name}<br />
		Location 		   : ${events.e_location}<br/>
		Date			   : ${events.e_date}<br/>	
		Description 	   : ${events.e_description}<br/>
		Total Participants : ${events.e_joined}<br/> 
	</div>
<a class="btn pull-right" href="<c:url value="/delete/${events.e_id}" />"><i class="icon-remove"></i> &nbsp;&nbsp;Delete Event</a><br><br><br>
<a class="btn pull-right" href="<c:url value="/edit/${events.e_id}" />"><i class="icon-edit"></i> &nbsp;&nbsp;Edit Event</a><br><br><br>			


<%@ include file="../footer.jsp" %>