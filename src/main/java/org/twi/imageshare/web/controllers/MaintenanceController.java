package org.twi.imageshare.web.controllers;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.twi.imageshare.services.ImageService;
import org.twi.imageshare.web.controllers.params.JsonResponse;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
	
	private static final String EMAIL = "";
	private static final String PASSWORD = "3444c0211ae0b5275bc6a7980f9d7ac4728bb6cbc75982e0026b6fc0507a4ab8";
	
	@Autowired
	private ApplicationContext context;

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;
	
	@Autowired
	private ReCaptcha recaptcha;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexView(HttpServletRequest paramHttpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		if (isAuthenticated(paramHttpServletRequest)) {
			modelAndView.addObject("stats", imageService.getDBStats());
			modelAndView.setViewName("maintenancePage");
		} else {
			modelAndView.setViewName("maintenanceAuthPage");
		}
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody JsonResponse maintenanceAuth(@RequestParam(value = "email", required=true) String email,
			@RequestParam(value = "password", required=true) String password,
			@RequestParam(value = "recaptcha_challenge_field", required=true) String challenge,
			@RequestParam(value = "recaptcha_response_field", required=true) String response,
			HttpServletRequest paramHttpServletRequest) {
		JsonResponse jsonResponse = new JsonResponse();
	    String remoteAddr = paramHttpServletRequest.getRemoteAddr();
	    ReCaptchaResponse reCaptchaResponse = recaptcha.checkAnswer(remoteAddr, challenge, response);
	    if (!reCaptchaResponse.isValid()) {
	    	jsonResponse.setCaptchaError(context.getMessage("error.bad.captcha", null, Locale.getDefault()));
	    	return jsonResponse;
	    }
		if (credentialsValid(email, password)) {
			jsonResponse.setResponse(paramHttpServletRequest.getRequestURL());
			paramHttpServletRequest.getSession().setAttribute("isAuthenticated", true);
		} else {
	    	jsonResponse.appendError(context.getMessage("error.bad.credentials", null, Locale.getDefault()));
		}
		return jsonResponse;
	}
	
	private boolean credentialsValid(String email, String password) {
		return email.toUpperCase().equals(EMAIL.toUpperCase()) && password.equals(PASSWORD);
	}
	
	private boolean isAuthenticated(HttpServletRequest paramHttpServletRequest) {
		Object result = paramHttpServletRequest.getSession().getAttribute("isAuthenticated");
		if (result != null) {
			return (Boolean)result;
		}
		return false;
	}
	
	@RequestMapping(value = "/clearDB", method = RequestMethod.POST)
	public @ResponseBody JsonResponse clearDB(HttpServletRequest paramHttpServletRequest) {
		JsonResponse response = new JsonResponse();
		try {
			if (isAuthenticated(paramHttpServletRequest)) {
				imageService.removeAll();
				response.setSuccess(context.getMessage("app.maintenance.db.clear.success", null, Locale.getDefault()));
			} else {
				throw new Exception();
			}			
		} catch (Exception e) {
			response.appendError(context.getMessage("app.maintenance.db.clear.error", null, Locale.getDefault()));
		}
		return response;
	}

}
