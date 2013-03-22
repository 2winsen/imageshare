package org.twi.imageshare.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.twi.imageshare.entities.Image;
import org.twi.imageshare.utils.ImageMIMETypes;

public class ImageValidator implements Validator {

	private static final long MAX_IMAGE_SIZE = 3145728; // 3 MB in bytes

	@Override
	public boolean supports(Class<?> arg0) {
		return Image.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors e) {
		Image image = (Image) obj;
		if (image.getFile() == null) {
			e.rejectValue("file", "error.nothing.to.upload");
			return;
		}
		String mime = image
				.getFile()
				.getContentType()
				.substring(image.getFile().getContentType().indexOf("/") + 1, image.getFile().getContentType().length())
				.toUpperCase();
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
