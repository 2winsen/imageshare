package org.twi.imageshare.web.controller;

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
import org.twi.imageshare.domainobject.Image;
import org.twi.imageshare.service.ImageService;
import org.twi.imageshare.web.validator.ImageValidator;

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
	public @ResponseBody AjaxResponse uploadAction(@Valid @ModelAttribute(value="image") Image image,
			BindingResult result,
			HttpServletRequest paramHttpServletRequest) {
			prepareImage(image);
		AjaxResponse response = new AjaxResponse();
		ImageValidator imageValidator = new ImageValidator();
		imageValidator.validate(image, result);
		if (!result.hasErrors()) {
			try {
				image = imageService.saveImage(image);
				response.setStatus("SUCCESS");
				response.setResponse(paramHttpServletRequest.getRequestURL() + image.getId());
			} catch (Exception e) {
				
			}
		} else {
			response.setStatus("ERROR");
			response.setErrors(result);
		}
		return response;
	}

	private void prepareImage(Image image) {
		image.setTimestamp(System.currentTimeMillis());
	}

}

class AjaxResponse {
	private String status;
	private Object response;
	private Object errors;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
