package org.twi.imageshare.web.controllers;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.twi.imageshare.services.ImageService;
import org.twi.imageshare.web.controllers.params.JsonResponse;

@Controller
@SessionAttributes("isAuthenticated")
@RequestMapping("/maintenance")
public class MaintenanceController {
	
	@Autowired
	private ApplicationContext context;

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;
	
	@Autowired
	private ReCaptcha recaptcha;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexView() {
		ModelAndView modelAndView = new ModelAndView();
		if (isAuthenticated()) {
			modelAndView.setViewName("maintenancePage");
		} else {
			modelAndView.setViewName("maintenanceAuthPage");
			modelAndView.addObject("stats", imageService.getDBStats());
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
	    }
	    
		if (credentialsValid()) {
			jsonResponse.setResponse(paramHttpServletRequest.getRequestURL());
			
//			modelAndView.addObject("isAuthenticated", true);  
//			modelAndView.addObject("stats", imageService.getDBStats());
//			modelAndView.setViewName("maintenancePage");
		} else {
	    	jsonResponse.appendError(context.getMessage("error.bad.credentials", null, Locale.getDefault()));
		}
		return jsonResponse;
	}
	
	private boolean credentialsValid() {
		return true;
	}
	
	private boolean isAuthenticated() {
		return false;
	}
	
	@RequestMapping(value = "/clearDB", method = RequestMethod.POST)
	public @ResponseBody JsonResponse clearDB(ModelMap model) {
		JsonResponse response = new JsonResponse();
		try {
			imageService.removeAll();
			response.setSuccess(context.getMessage("app.maintenance.db.clear.success", null, Locale.getDefault()));
		} catch (Exception e) {
			response.appendError(context.getMessage("app.maintenance.db.clear.error", null, Locale.getDefault()));
		}
		return response;
	}

}
