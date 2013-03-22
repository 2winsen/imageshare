package org.twi.imageshare.web.controllers.params;

import java.util.ArrayList;
import java.util.List;

public class JsonResponse {

	private Object response;
	private List<String> errors;

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
	
	public void appendError(String error) {
		if (errors == null) {
			errors = new ArrayList<String>();
		}
		errors.add(error);
	}

}
