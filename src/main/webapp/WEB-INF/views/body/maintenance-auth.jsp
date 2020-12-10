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
				<td style="padding-right: 50px;">Email:</td>
				<td style="width: 500px;"><input id="email" name="email" type="text" placeholder="Email"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input id="password" name="password" type="password" placeholder="Password"></td>
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

<script src="<c:url value="/resources/app/js/pages/maintenance-auth.js"/>" type="text/javascript" charset="utf-8"></script>
