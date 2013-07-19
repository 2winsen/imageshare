<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
	<h3 style="padding-bottom: 20px;">
		<spring:message code="app.title.maintenance" />
	</h3>
	
	 <form id="maintenanceAuthForm" action="#" method="post">
		<table>
			<tr>
				<td>Email:</td>
				<td><input id="email" name="email" type="text" placeholder="Email"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input id="password" name="password" type="password" placeholder="Password"></td>
			</tr>
			<tr>
				<td colspan="2" style="padding-top: 20px;">
					<div id="captchaErrorContainer"></div>
					<%@ include file="/WEB-INF/views/body/recaptcha.jsp" %>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-top: 20px;">
					<button type="submit" class="btn btn-primary" style="width: 100px;">Auth</button>
				</td>
			</tr>
			
		</table>	 
	 </form>
	
	<form id="maintenanceAuthForm" method="post">
	</form>

</div>

<script src="<c:url value="/resources/imageshare/js/pages/maintenance.js"/>" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#wrap").attr("style", "margin-bottom: -80px");
	});
</script>
