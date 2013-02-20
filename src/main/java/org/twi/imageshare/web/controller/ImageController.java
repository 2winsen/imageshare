package org.twi.imageshare.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.twi.imageshare.service.ImageService;

/**
 * RESTful controller to access images by URI
 * 
 * @author vitalijs.sakels
 * 
 */
@Controller
@RequestMapping("/")
public class ImageController {

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;

	@RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
	public String findOwner(@PathVariable String imageId, Model model) {
		
		imageService.getImage(imageId);
		
		model.addAttribute("imageId", imageId);		 
		return "image";
	}
	
}
