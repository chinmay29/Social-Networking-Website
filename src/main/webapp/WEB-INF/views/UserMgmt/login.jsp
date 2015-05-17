<%@ include file="../loginheader.jsp"%>

<c:if test="${not empty errorMessage}">
	<div class="errorMessage">${errorMessage}</div>
</c:if>

<div class="container-fluid">
	<div class="row-fluid">
		<a href="${pageContext.request.contextPath}/register"
			class="btn pull-right login-register-btns home-buttons"><i
			class="icon-leaf"></i> &nbsp;&nbsp;Register</a>
	</div>
</div>

<h3>Login</h3>
<br />

<div class="centered">
	<form method="post"
		action="${pageContext.request.contextPath}/verifylogin"
		class="login-form">
		<input type="email" name="email" placeholder="E-mail" required
			maxlength="50" pattern=".{8,50}" title="E-mail should be between 8 and 50 characters" class="input-block-level" /> <input type="password"
			name="password" placeholder="Password" required maxlength="50" pattern=".{8,50}" title="Password should be between 8 and 50 characters"
			class="input-block-level" /> <input type="submit" value="Submit"
			class="btn pull-right" />
	</form>
</div>

<%@ include file="../loginfooter.jsp"%>