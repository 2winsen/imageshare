package org.twi.imageshare.web.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.twi.imageshare.services.ImageService;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;

	@RequestMapping(method = RequestMethod.GET)
	public String indexView() {
		return "maintenanceAuthPage";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String maintenanceAuth(ModelMap model) {
		model.addAttribute("stats", imageService.getDBStats());
		return "maintenancePage";
	}

}
