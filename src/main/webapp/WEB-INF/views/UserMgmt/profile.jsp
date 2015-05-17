<%@ include file="../header.jsp"%>

<div>

	<a href="${pageContext.request.contextPath}/editProfile" class="btn">Edit</a>
	<br />
	<br /> <label><b>E-mail:</b> <c:if test="${not empty user}">
			<c:out value="${user.getEmail()}"></c:out>
		</c:if></label> <label><b>First Name:</b> <c:if test="${not empty user}">
			<c:out value="${user.getFirstName()}"></c:out>
		</c:if></label> <label><b>Last Name:</b> <c:if test="${not empty user}">
			<c:out value="${user.getLastName()}"></c:out>
		</c:if></label>

</div>

<%@ include file="../footer.jsp"%>