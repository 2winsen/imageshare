package org.twi.imageshare.web.controllers;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexView() {
		return new ModelAndView("maintenanceAuthPage");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView maintenanceAuth(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isAuthenticated", true);  
		modelAndView.addObject("stats", imageService.getDBStats());		
		modelAndView.setViewName("maintenancePage");		
		return modelAndView;
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
