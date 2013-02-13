<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Imageshare</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
	</head>
	
  	<body>
	    <div>
	    	<tiles:insertAttribute name="header" />
    		<tiles:insertAttribute name="body" /> 
	    	<tiles:insertAttribute name="footer" />
	    </div>
		<script src="<c:url value="/resources/jquery/jquery-1.9.1.min.js" />"></script>
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	</body>
</html>
