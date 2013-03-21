package org.twi.imageshare.web.controllers.params;

public class JsonResponse {

	private Object response;
	private Object errors;

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

}
