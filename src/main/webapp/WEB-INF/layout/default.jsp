<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="app.name" /> <spring:message code="app.slogan" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<c:url value="/resources/vendors/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/app/assets/style/style.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/vendors/bootstrap-fileupload/bootstrap-fileupload.min.css" />" rel="stylesheet">

	<script src="<c:url value="/resources/vendors/jquery/jquery-1.9.1.min.js"/>" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/resources/vendors/bootstrap/js/bootstrap.min.js"/>" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/resources/vendors/jquery/jquery.form.js" />" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/resources/vendors/sha256.js" />" type="text/javascript" charset="utf-8"></script>

	<script src="<c:url value="/resources/app/js/common.js"/>" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var CONTEXT_PATH="<%=request.getContextPath()%>";
	</script>
</head>

<body>
	<noscript>
		<h2><spring:message code="error.no.js" /></h2>
	</noscript>

	<div id="wrap">

		<!-- Begin page content -->
		<div class="container">
			<div class="page-header">
				<tiles:insertAttribute name="header" />
			</div>
			<div>
				<div id="messagesContainer"></div>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div id="push"></div>
	</div>

	<div id="footer">
		<div class="container">
			<div class="muted credit">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>

</body>
</html>
