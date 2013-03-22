<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>

	<h1>
		<a href="<c:url value="/" />"><spring:message code="app.name" /></a>
	</h1>
	<h3>
		<spring:message code="app.slogan" />
	</h3>
</div>