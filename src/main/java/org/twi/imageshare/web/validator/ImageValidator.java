package org.twi.imageshare.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.twi.imageshare.domainobject.Image;
import org.twi.imageshare.utils.ImageMIMETypes;

public class ImageValidator implements Validator {

	private static final long MAX_IMAGE_SIZE = 5242880; // 5 MB
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Image.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors e) {
		Image image = (Image)obj;
		String mime = image.getFile().getContentType().substring(
				image.getFile().getContentType().indexOf("/") + 1, 
				image.getFile().getContentType().length()
				).toUpperCase();
		for (ImageMIMETypes supportedMime : ImageMIMETypes.values()) {
			if (mime.contains(supportedMime.toString())) {
				if (image.getFile().getSize() > MAX_IMAGE_SIZE) {
					e.rejectValue("file", "error.image.too.big");
				}
				return;
			}
		}
		e.rejectValue("file", "error.image.of.not.supported.type");
	}

}
