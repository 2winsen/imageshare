<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="app.name" />
	<spring:message code="app.slogan" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/imageshare/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/bootstrap-fileupload/bootstrap-fileupload.min.css" />" rel="stylesheet">
<!-- JS -->
<script src="<c:url value="/resources/jquery/jquery-1.9.1.min.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/jquery/jquery.form.js" />" type="text/javascript" charset="utf-8"></script>
</head>

<body>
	<div id="wrap">

		<!-- Begin page content -->
		<div class="container">
			<div class="page-header">
				<tiles:insertAttribute name="header" />
			</div>
			<div style="text-align: center;">
				<div id="errorsContainer"></div>
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
