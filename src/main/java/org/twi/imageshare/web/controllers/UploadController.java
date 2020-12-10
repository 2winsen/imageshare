package org.twi.imageshare.web.controllers;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.twi.imageshare.entities.Image;
import org.twi.imageshare.services.ImageService;
import org.twi.imageshare.web.controllers.params.JsonResponse;
import org.twi.imageshare.web.validators.ImageValidator;

/**
 * MVC Controller for uploading Images (default page)
 * 
 * @author vitalijs.sakels
 * 
 */
@Controller
@RequestMapping("/")
public class UploadController {

	public static final Logger log = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private ApplicationContext context;

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexView() {
		return new ModelAndView("uploadPage", "image", new Image());
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse uploadAction(@Valid @ModelAttribute(value = "image") Image image,
	        BindingResult result,
			HttpServletRequest paramHttpServletRequest) {
		JsonResponse jsonResponse = new JsonResponse();
		
		prepareImage(image);
		(new ImageValidator()).validate(image, result);
		if (!result.hasErrors()) {
			try {
				image.setBytes(image.getFile().getBytes());
				image.setContentType(image.getFile().getContentType());
				image = imageService.saveImage(image);
				jsonResponse.setResponse(paramHttpServletRequest.getRequestURL() + image.getId());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else {
			for (ObjectError error : result.getAllErrors()) {
				jsonResponse.appendError(context.getMessage(error.getCode(), null, Locale.getDefault()));
			}
		}
		return jsonResponse;
	}

	private void prepareImage(Image image) {
		image.setTimestamp(System.currentTimeMillis());
	}

}
