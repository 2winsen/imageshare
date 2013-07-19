package org.twi.imageshare.web.controllers;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

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
	
	@Autowired
	private ReCaptcha recaptcha;

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
		return new ModelAndView("uploadPage", "image", new Image());
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
	JsonResponse uploadAction(@Valid @ModelAttribute(value = "image") Image image, 
			@RequestParam(value = "captcha_challenge", required=true) String challenge,
			@RequestParam(value = "captcha_response", required=true) String response, 
	        BindingResult result,
			HttpServletRequest paramHttpServletRequest) {
		JsonResponse jsonResponse = new JsonResponse();
	    String remoteAddr = paramHttpServletRequest.getRemoteAddr();
	    ReCaptchaResponse reCaptchaResponse = recaptcha.checkAnswer(remoteAddr, challenge, response);
	    if (!reCaptchaResponse.isValid()) {
	    	jsonResponse.setCaptchaError(context.getMessage("error.bad.captcha", null, Locale.getDefault()));
	    	return jsonResponse;
	    }
		
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
