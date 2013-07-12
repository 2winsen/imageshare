<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>
		<spring:message code="app.status.title" />
	</h3>
	<c:forEach var="stat" items="${stats}">
		<div style="padding: 5px 5px 5px 5px;">
			<span style="color: gray;">${stat.key}:</span><span style="font-weight: bold;">${stat.value}</span>
		</div>
	</c:forEach>
</div>
