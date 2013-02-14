package org.twi.imageshare.web.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.twi.imageshare.service.ImageService;

@Controller
@RequestMapping("/")
public class UploadController {

	@Resource(name="imageService")
	private ImageService imageService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String initView(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String uploadAction(@RequestParam MultipartFile file, ModelMap model) {
		System.out.println("Oaaaa");
		return "index";
	}

}
