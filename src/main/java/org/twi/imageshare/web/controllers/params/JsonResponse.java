package org.twi.imageshare.web.controllers.params;

import java.util.ArrayList;
import java.util.List;

public class JsonResponse {

	private Object response;
	private List<String> errors;
	private String captchaError;

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getCaptchaError() {
		return captchaError;
	}

	public void setCaptchaError(String captchaError) {
		this.captchaError = captchaError;
	}

	public void appendError(String error) {
		if (errors == null) {
			errors = new ArrayList<String>();
		}
		errors.add(error);
	}

}
