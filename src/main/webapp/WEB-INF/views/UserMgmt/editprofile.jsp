<%@ include file="../header.jsp"%>

<form method="post" class="edit-profile"
	action="${pageContext.request.contextPath}/editUserProfile">

	<input class="input-block-level" type="email" name="email"
		value="${user.getEmail()}" placeholder="E-mail" /> <input
		class="input-block-level" type="text" name="firstName"
		value="${user.getFirstName()}" placeholder="First Name" /> <input
		class="input-block-level" type="text" name="lastName"
		value="${user.getLastName()}" placeholder="Last Name" /> <input
		type="submit" name="submit" value="Submit" class="btn pull-right" />

</form>

<%@ include file="../footer.jsp"%>