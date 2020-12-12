<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="upload">
    <form:form id="uploadForm" method="post" action="/" modelAttribute="image">
        <div class="preview"></div>
        <input id="imageFile" type="file" />
        <label for="imageFile"><strong>Choose a file</strong><span style="display: inline;"> or drag it here</span>.</label>
    </form:form>
    <label class="checkbox inline" style="padding-bottom: 10px; padding-top: 10px;">
        <input id="termsCheckbox" type="checkbox" name="checkbox" /><span style="padding-left: 5px;"><spring:message code="app.upload.i.agree" />&nbsp;<a href="<c:url value="/terms" />"><spring:message code="app.terms.link" /></a></span>
    </label>
    <div>
        <input id="share1" class="btn btn-large btn-primary" type="button" value="<spring:message code="app.upload.continue" />" disabled="disabled" />
    </div>

    <div id="commentModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="commentModal" aria-hidden="true">
    	<div class="modal-header">
    		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&#x2715;</button>
    		<h4 id="myModalLabel">
    			<spring:message code="app.upload.comment" />
    		</h4>
    	</div>
    	<div class="modal-body">
    		<textarea id="comment" rows="2" cols="50" maxlength="200"></textarea>
    	</div>

    	<div class="modal-footer">
    		<button id="share2" class="btn btn-danger btn-primary" data-dismiss="" aria-hidden="false">
    			<spring:message code="app.upload.share" />
    		</button>
    	</div>
    </div>
</div>

<script src="<c:url value="/resources/app/js/pages/upload.js"/>" type="text/javascript" charset="utf-8"></script>
