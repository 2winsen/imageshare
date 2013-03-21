package org.twi.imageshare.web.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;

	/**
	 * Rendering index page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexView() {
		return new ModelAndView("index", "image", new Image());
	}

	/**
	 * AJAX Called once user is submitting upload form
	 * 
	 * @param model
	 * @param file
	 *            - Uploaded file
	 * @param comment
	 *            - Comment for uploaded file
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse uploadAction(@Valid @ModelAttribute(value = "image") Image image, BindingResult result,
			HttpServletRequest paramHttpServletRequest) {
		prepareImage(image);
		JsonResponse response = new JsonResponse();
		ImageValidator imageValidator = new ImageValidator();
		imageValidator.validate(image, result);
		if (!result.hasErrors()) {
			try {
				image = imageService.saveImage(image);
				response.setResponse(paramHttpServletRequest.getRequestURL() + image.getId());
			} catch (Exception e) {

			}
		} else {
			response.setErrors(result);
		}
		return response;
	}

	private void prepareImage(Image image) {
		image.setTimestamp(System.currentTimeMillis());
	}

}
