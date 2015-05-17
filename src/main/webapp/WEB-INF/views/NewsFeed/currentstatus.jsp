<%@ include file="../header.jsp"%>

this is current status of you and your friends

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
		<td>Datetime</td>&nbsp;
		<td>Like</td>&nbsp;
		<td>Flag</td>
	</tr>

	<c:forEach var="mycurrentupdates" items="${mycurrentupdates}">
		<tr>

			<td><c:out
					value="${mycurrentupdates.getClass() eq 'class com.connect.models.NewsFeed.Comment'?'Comment':'Message'}"></c:out></td>

			<td><c:out value="${mycurrentupdates.name}"></c:out></td>

			<td><c:out value="${mycurrentupdates.id}"></c:out></td>

			<td><c:out value="${mycurrentupdates.text}"></c:out></td>

			<td><c:out value="${mycurrentupdates.timestamp}"></c:out></td>

			<td><c:out value="${mycurrentupdates.like}"></c:out></td>

			<td><c:out value="${mycurrentupdates.flag}"></c:out></td>

		</tr>
	</c:forEach>
</table>

<form method="post"
	action="${pageContext.request.contextPath}/addcomment">

	<table class="formtable">
		<tr>
			<td class="label">Enter the referenceid:</td>
			<td><input type="text" class="control" path="id" name="id"><br /></td>
		</tr>
		<tr>
			<td class="label">Enter your comment:</td>
			<td><textarea class="control" path="newcomment"
					name="newcomment" rows="10" cols="10"></textarea><br /></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Create comment" type="submit" /></td>
		</tr>
	</table>
</form>

<form method="post"
	action="${pageContext.request.contextPath}/likeclicked">

	<table class="formtable">
		<tr>
			<td class="label">Enter the referenceid:</td>
			<td><input type="text" class="control" path="id" name="id"><br /></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="like status" type="submit" /></td>
		</tr>
	</table>
</form>

<form method="post"
	action="${pageContext.request.contextPath}/flagclicked">

	<table class="formtable">
		<tr>
			<td class="label">Enter the referenceid:</td>
			<td><input type="text" class="control" path="id" name="id"><br /></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="flag status" type="submit" /></td>
		</tr>
	</table>
</form>

<%@ include file="../footer.jsp"%>