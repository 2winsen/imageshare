package org.twi.imageshare.web.controllers;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.twi.imageshare.services.ImageService;
import org.twi.imageshare.web.controllers.params.JsonResponse;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
	public static final Logger log = LoggerFactory.getLogger(MaintenanceController.class);

	private static final String EMAIL = System.getenv("MAINTENANCE_EMAIL");
	private static final String PASSWORD = System.getenv("MAINTENANCE_PASSWORD");

	private static final String SESSION_ATTR_IS_AUTHENTICATED = "isAuthenticated";
	private static final String SESSION_ATTR_LOGIN_ATTEMPT = "loginAttempt";
	
	@Autowired
	private ApplicationContext context;

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexView(HttpServletRequest paramHttpServletRequest, 
			@RequestParam(value = "success", required=false) Boolean successRedirectAttr) {
		ModelAndView modelAndView = new ModelAndView();
		if (isAuthenticated(paramHttpServletRequest)) {
			modelAndView.addObject("stats", imageService.getDBStats());
			modelAndView.setViewName("maintenancePage");
			if (successRedirectAttr != null) {
				if (successRedirectAttr) {
					modelAndView.addObject("success", context.getMessage("app.maintenance.db.clear.success", null, Locale.getDefault()));
				} else {
					modelAndView.addObject("error", context.getMessage("app.maintenance.db.clear.error", null, Locale.getDefault()));
				}
			}
		} else {
			modelAndView.setViewName("maintenanceAuthPage");
		}
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody JsonResponse maintenanceAuth(@RequestParam(value = "email", required=true) String email,
			@RequestParam(value = "password", required=true) String password,
			HttpServletRequest paramHttpServletRequest) {
		JsonResponse jsonResponse = new JsonResponse();
		if (credentialsValid(email, password)) {
			jsonResponse.setResponse(paramHttpServletRequest.getRequestURL());
			paramHttpServletRequest.getSession().setAttribute(SESSION_ATTR_IS_AUTHENTICATED, true);			
		} else {
			Integer attempts = (Integer)paramHttpServletRequest.getSession().getAttribute(SESSION_ATTR_LOGIN_ATTEMPT);
			if (attempts == null) {
				attempts = 0;
			} 
			paramHttpServletRequest.getSession().setAttribute(SESSION_ATTR_LOGIN_ATTEMPT, ++attempts);
	    	jsonResponse.appendError(context.getMessage("error.bad.credentials", null, Locale.getDefault()));
		}
		return jsonResponse;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String maintenanceLogout(HttpServletRequest paramHttpServletRequest) {
		paramHttpServletRequest.getSession().invalidate();
		return "redirect:/maintenance";
	}
	
	private boolean credentialsValid(String email, String password) {
		return email.toUpperCase().equals(EMAIL.toUpperCase()) && password.equals(PASSWORD);
	}
	
	private boolean isAuthenticated(HttpServletRequest paramHttpServletRequest) {
		Object result = paramHttpServletRequest.getSession().getAttribute(SESSION_ATTR_IS_AUTHENTICATED);
		if (result != null) {
			return (Boolean)result;
		}
		return false;
	}
	
	@RequestMapping(value = "/clearDB", method = RequestMethod.POST)
	public String clearDB(HttpServletRequest paramHttpServletRequest, RedirectAttributes redirectAttributes) {
		try {
			if (isAuthenticated(paramHttpServletRequest)) {
				imageService.removeAll();
				redirectAttributes.addAttribute("success", true);
			} else {
				throw new Exception();
			}			
		} catch (Exception e) {
			redirectAttributes.addAttribute("success", false);
		}
		return "redirect:/maintenance";
	}

}
