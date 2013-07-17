<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="text-align: center;">
	<h3>
		<spring:message code="app.status.title" />
	</h3>
	
	<div style="padding-top: 10px; padding-bottom: 10px;">
		<input id="clearDBButton" type="button" class="btn btn-danger btn-primary" value="Clear DB!?" />
	</div>
	
	<c:forEach var="stat" items="${stats}">
		<div style="padding: 5px 5px 5px 5px;">
			<span style="color: gray;">${stat.key}:</span><span style="font-weight: bold;">${stat.value}</span>
		</div>
	</c:forEach>
</div>

<script src="<c:url value="/resources/imageshare/js/pages/maintenance.js"/>" type="text/javascript" charset="utf-8"></script>