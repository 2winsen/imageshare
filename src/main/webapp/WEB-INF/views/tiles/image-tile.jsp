<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<img src="<c:url value="/image_src/${image.id}" />" />
	<h2>${image.comment}</h2>
	<h2>${image.timestamp}</h2>
</div>