<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var RecaptchaOptions = {
		lang : 'en',
		theme : 'clean'
	};
</script>

<div>
	<h3 style="padding-bottom: 20px;">
		<spring:message code="app.title.maintenance" />
	</h3>
	
	<form id="maintenanceAuthForm" method="post">
		<table>
			<tr>
				<td>Email:</td>
				<td><input type="text" id="inputEmail" placeholder="Email"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" id="inputPassword" placeholder="Password"></td>
			</tr>
			<tr>
				<td colspan="2" style="padding-top: 20px;">
					<script type="text/javascript" src="http://api.recaptcha.net/challenge?k=6LfWX-QSAAAAALqL0OXbCI5OEnPDRjZEsCZaBo5H"></script>
					<noscript>
						<iframe src="http://api.recaptcha.net/noscript?k=6LfWX-QSAAAAALqL0OXbCI5OEnPDRjZEsCZaBo5H" height="300" width="500" frameborder="0"></iframe><br>
						<textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
						<input type="hidden" name="recaptcha_response_field" value="manual_challenge">
					</noscript>			
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-top: 20px;">
					<button type="submit" class="btn btn-primary" style="width: 100px;">Auth</button>
				</td>
			</tr>
			
		</table>
	</form>

</div>

<script src="<c:url value="/resources/imageshare/js/pages/maintenance-auth.js"/>" type="text/javascript" charset="utf-8"></script>
