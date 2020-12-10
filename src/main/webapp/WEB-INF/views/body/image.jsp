<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="text-align: center;" class="image">
    <span id="copied-notification">Copied!</span>
	<div class="input-append copy-to-clipboard">
		<input readonly id="url-input"  type="text" value="${url}">
		<button id="copy-to-clipboard-btn" class="btn" data-clipboard-text="${url}" data-original-title="<spring:message code="app.image.copied.to.clipboard" />"><spring:message code="app.image.copy" /></span>
	</div>

	<img src="<c:url value="/image_src/${imageId}" />" />
	<div style="padding-top: 30px;">
		<div style="text-align: left; font-weight: bold;">
			<h4>
				<spring:message code="app.image.comment" />
			</h4>
		</div>
		<div class="well">
			<span>${comment}</span>
		</div>
		<div style="padding-bottom: 40px; text-align: center; font-weight: bold;">
			<fmt:formatDate pattern="dd MMM yyyy HH:mm:ss" value="${date}" />
		</div>
	</div>
</div>

<script src="<c:url value="/resources/app/js/pages/image.js"/>" type="text/javascript" charset="utf-8"></script>
