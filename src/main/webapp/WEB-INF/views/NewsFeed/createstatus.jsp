<%@ include file="../header.jsp"%>

<p>
	<a href="${pageContext.request.contextPath}/newsfeed">Go to
		newsfeed homepage</a>
</p>

<form method="post" action="${pageContext.request.contextPath}/docreate">

	<table class="formtable">
		<tr>
			<td class="label">Enter your message:</td>
			<td><textarea class="control" path="statusmessage"
					name="statusmessage" rows="10" cols="10"></textarea><br /></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Create message" type="submit" /></td>
		</tr>
	</table>

</form>

<%@ include file="../footer.jsp"%>