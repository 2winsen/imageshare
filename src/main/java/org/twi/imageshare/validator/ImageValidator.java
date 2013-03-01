package org.twi.imageshare.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.twi.imageshare.domainobject.Image;

public class ImageValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Image.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		int a = 10;
		int b = 20;
	}

}
