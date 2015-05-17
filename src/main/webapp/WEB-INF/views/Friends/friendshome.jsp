
<%@ include file="../header.jsp" %>

<p><a href="${pageContext.request.contextPath}/showfriends" class="btn btn-success">All Friends</a>
<a href="${pageContext.request.contextPath}/recentfriends" class="btn btn-success">Recently Added Friends</a>

<a href="${pageContext.request.contextPath}/getfriendReq" class="btn btn-success">Friend Requests</a>

<table>
	<tr>
		<form method="post" action="${pageContext.request.contextPath}/findperson" >
		<td>Search :</td>
		<td><input type="text" name="name"></td>
		<td><input type="submit" value="Search" class="btn btn-info"></td>
		</form>
	</tr>
	<tr>	
		<form method="post" action="${pageContext.request.contextPath}/findfriend" >
		<td>Search a Friend:</td>
		<td><input type="text" name="name"></td>
		<td><input type="submit" value="Search" class="btn btn-info"></td>
		</form>
	</tr>
</table>
</p>		
<%@ include file="../footer.jsp" %>