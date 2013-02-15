package org.twi.imageshare.web.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.twi.imageshare.domainobject.Image;
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
	public String uploadAction(@RequestParam MultipartFile file, @RequestParam String comment, ModelMap model) {
		Image image = null;
		try {
			image = prepareImage(file);
		} catch (IOException e) {
			e.printStackTrace();
			return "uncaughtException";
		}
		imageService.saveImage(image);
		return "index";
	}
	
	private Image prepareImage(MultipartFile multipartFile) throws IOException {
		Image image = new Image();
		image.setBytes(multipartFile.getBytes());
		image.setComment("Comment Comment Comment Comment");
		image.setTimestamp(System.currentTimeMillis());		
		return image;
	}

}
