<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="fileupload fileupload-new" data-provides="fileupload">
	<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
		<img src="<c:url value="/resources/imageshare/img/no_preview.png"/>" />
	</div>
	<div class="fileupload-preview fileupload-exists thumbnail"
		style="max-width: 300px; max-height: 300px; line-height: 20px;"></div>
	<div>
		<form:form id="uploadForm" method="post" modelAttribute="image">
			<form:input id="comment" type="hidden" path="comment" />
			<span class="btn btn-link btn-file"> <span class="fileupload-new"><spring:message
						code="app.upload.select.image" /></span> <span class="fileupload-exists"><spring:message code="app.upload.change" /></span>
				<form:input id="imageFile" type="file" value="browse" path="file" />
			</span>
			<a href="#" class="btn btn-link fileupload-exists" data-dismiss="fileupload"><spring:message
					code="app.upload.remove" /></a>
		</form:form>
		
			<label class="checkbox inline" style="padding-bottom: 10px; padding-top: 10px;">
				<input id="termsCheckbox" type="checkbox" name="checkbox" /><span style="padding-left: 5px;"><spring:message code="app.upload.i.agree" />&nbsp;<a href="<c:url value="/terms" />"><spring:message code="app.terms.link" /></a></span>
			</label>
			<div>
			<input id="share1" class="btn btn-large btn-primary" type="button"
				value="<spring:message code="app.upload.continue" />" disabled="disabled" />
		</div>
	</div>
</div>

<!-- Modal -->
<div id="commentModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="commentModal"
	aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&#x2715;</button>
		<h4 id="myModalLabel">
			<spring:message code="app.upload.comment" />
		</h4>
	</div>
	<div class="modal-body">
		<textarea id="commentTemp" rows="2" cols="50" maxlength="200"
			style="margin: 0px 0px 10px; height: 112px; width: 516px;"></textarea>
	</div>
	<div class="modal-footer">
		<button id="share2" class="btn btn-large btn-primary" data-dismiss="modal" aria-hidden="true">
			<spring:message code="app.upload.share" />
		</button>
	</div>
</div>
<script src="<c:url value="/resources/imageshare/js/pages/upload.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/bootstrap-fileupload/bootstrap-fileupload.min.js"/>" type="text/javascript"
	charset="utf-8"></script>
