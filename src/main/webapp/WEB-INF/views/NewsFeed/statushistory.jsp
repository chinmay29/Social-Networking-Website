<%@ include file="../header.jsp"%>

this is status history

<p>
	<a href="${pageContext.request.contextPath}/newsfeed">Go to
		newsfeed homepage</a>
</p>


<table class="">
	<tr>
		<td>Type</td>
		<td>User_Name</td>
		<td>id</td>
		<td>Message</td>
		<td>Datetime</td>
		<td>Likes</td>
		<td>Flag</td>
	</tr>

	<c:forEach var="mystatushistory" items="${mystatushistory}">
		<tr>

			<td><c:out
					value="${mystatushistory.getClass() eq 'class com.connect.models.NewsFeed.Comment'?'Comment':'Message'}"></c:out></td>

			<td><c:out value="${mystatushistory.name}"></c:out></td>

			<td><c:out value="${mystatushistory.id}"></c:out></td>

			<td><c:out value="${mystatushistory.text}"></c:out></td>

			<td><c:out value="${mystatushistory.timestamp}"></c:out></td>

			<td><c:out value="${mystatushistory.like}"></c:out></td>

			<td><c:out value="${mystatushistory.flag}"></c:out></td>


		</tr>
	</c:forEach>
</table>

<form method="post"
	action="${pageContext.request.contextPath}/editstatus">

	Use this forum to edit your past message/comment
	<table class="formtable">
		<tr>
			<td class="label">Enter the referenceid:</td>
			<td><input type="text" class="control" path="id" name="id"><br /></td>
		</tr>
		<tr>
			<td class="label">Enter your status you would like to change:</td>
			<td><textarea class="control" path="newstatus" name="newstatus"
					rows="10" cols="10"></textarea><br /></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="edit status" type="submit" /></td>
		</tr>
	</table>
</form>

<form method="post"
	action="${pageContext.request.contextPath}/deleteclicked">

	<table class="formtable">
		<tr>
			<td class="label">Enter the referenceid:</td>
			<td><input type="text" class="control" path="id" name="id"><br /></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="delete status" type="submit" /></td>
		</tr>
	</table>
</form>




<%@ include file="../footer.jsp"%>