<%@ include file="../header.jsp"%>
<a href="http://localhost:8080/connect/search" class="btn pull-right"><i class="icon-search"></i> &nbsp;&nbsp;Search Event</a> <br /> <br /> <br/>

<a href="<c:url value="/joinEvent/${events.e_id}" />" class="btn pull-right"><i class="icon-plus"></i> &nbsp;&nbsp;Join Event</a><br /> <br /> 

	<div style="margin-top: -80px;">
		Event Name 		   : ${events.e_name}<br />
		Location 		   : ${events.e_location}<br/>
		Date			   : ${events.e_date}<br/>	
		Description 	   : ${events.e_description}<br/>
		Total Participants : ${events.e_joined}<br/> 
	</div>

<a class="btn pull-right" href="<c:url value="/leaveEvent/${events.e_id}" />"><i class="icon-minus"></i> &nbsp;&nbsp;Leave Event</a><br><br><br>

<%@ include file="../footer.jsp" %>