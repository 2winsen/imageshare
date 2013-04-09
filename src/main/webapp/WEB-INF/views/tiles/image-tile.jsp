<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div>
	<div class="input-append">
		<input id="ZeroClipboard_swf" type="hidden" value="<c:url value="/resources/zeroclipboard/ZeroClipboard.swf"/>" /> 
		<input style="width: 500px;" readonly class="span2" id="appendedInputButton" type="text" value="${url}">
		<button id="copyToClipboard" class="btn" data-clipboard-text="${url}" type="button"><spring:message code="app.image.copy" /></button>
	</div>

	<img src="<c:url value="/image_src/${imageId}" />" />
	<div style="padding-top: 30px;">
		<div style="text-align: left; font-weight: bold;">
			<h4>
				<spring:message code="app.image.comment" />
			</h4>
		</div>
		<div class="well" style="height: 82px; margin: 5px 5px 5px 5px; text-align: left;">
			<span>${comment}</span>
		</div>
		<div style="padding-bottom: 40px; text-align: center; font-weight: bold;">
			<fmt:formatDate pattern="dd MMM yyyy HH:mm:ss" value="${date}" />
		</div>
	</div>
</div>
<script src="<c:url value="/resources/zeroclipboard/ZeroClipboard.min.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/imageshare/js/pages/image.js"/>" type="text/javascript" charset="utf-8"></script>