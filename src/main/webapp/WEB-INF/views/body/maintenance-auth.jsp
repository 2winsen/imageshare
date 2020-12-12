<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="maintenance-auth">
	<h3 class="section-header">
		<spring:message code="app.title.maintenance" />
	</h3>
	
	 <form id="maintenanceAuthForm" method="post" action="/maintenance">
		<table>
			<tr>
				<td class="form-label">Email:</td>
				<td><input id="email" name="email" type="text" placeholder="Email"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input id="password" name="password" type="password" placeholder="Password"></td>
			</tr>
			<tr>
				<td colspan="2" class="action-container">
					<button type="submit" class="btn btn-primary">Auth</button>
				</td>
			</tr>
		</table>	 
	 </form>

</div>

<script src="<c:url value="/resources/app/js/pages/maintenance-auth.js"/>" type="text/javascript" charset="utf-8"></script>
