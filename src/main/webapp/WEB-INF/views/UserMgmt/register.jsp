<%@ include file="../loginheader.jsp"%>


<div class="container-fluid">
	<div class="row-fluid">
		<a href="${pageContext.request.contextPath}/login"
			class="btn pull-right login-register-btns home-buttons"><i
			class="icon-home"></i> &nbsp;&nbsp;Login</a>
	</div>
</div>

<h3>Register</h3>
<br />

<div class="centered">
	<form method="post"
		action="${pageContext.request.contextPath}/registerUser"
		class="login-form">
		<input type="email" name="email" placeholder="E-mail" required
			maxlength="50" pattern=".{8,50}" title="E-mail should be between 8 and 50 characters" class="input-block-level" /> <input type="password"
			name="password" placeholder="Password" required maxlength="50" pattern=".{8,50}" title="Password should be between 8 and 50 characters"
			class="input-block-level" /> <input type="text" name="firstName"
			placeholder="First Name" required maxlength="50" pattern=".{3,40}" title="First Name should be between 3 and 40 characters"
			class="input-block-level" /> <input type="text" name="lastName"
			placeholder="Last Name" maxlength="40" class="input-block-level" />
		
		<input type="submit" value="Submit" class="btn pull-right" />
	</form>
</div>


<%@ include file="../loginfooter.jsp"%>