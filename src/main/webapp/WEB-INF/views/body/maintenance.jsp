<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="text-align: center;" class="maintenance">
	<h3 class="section-header">
		<spring:message code="app.status.title" />
	</h3>
	
	<form action="maintenance/logout" method="post">
		<input id="clearDBButton" type="submit" class="btn btn-primary" value="Logout" />
	</form>
	
	<c:forEach var="stat" items="${stats}">
		<div style="padding: 5px 5px 5px 5px;">
			<span style="color: gray;">${stat.key}:</span><span style="font-weight: bold;">${stat.value}</span>
		</div>
	</c:forEach>

    <form style="padding-top: 10px; padding-bottom: 10px;" action="maintenance/clearDB" method="post">
        <input id="successMessage" type="hidden" value="${success}" />
        <input id="errorMessage" type="hidden" value="${error}" />
        <input id="clearDBButton" type="submit" class="btn btn-danger btn-primary" value="Clear DB!?" />
    </form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		if ($("#successMessage").val() != "") {
			CommonModule.showSuccessMessage($("#successMessage").val());
		} else if ($("#errorMessage").val() != "") {
			CommonModule.showErrorMessages([$("#errorMessage").val()]);
		}
	});
</script>
