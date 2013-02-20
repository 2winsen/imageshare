package org.twi.imageshare.web.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.twi.imageshare.domainobject.Image;
import org.twi.imageshare.service.ImageService;

/**
 * MVC Controller for uploading Images 
 * @author vitalijs.sakels
 *
 */
@Controller
@RequestMapping("/")
public class UploadController {

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String initView() {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String uploadAction(HttpServletRequest paramHttpServletRequest,
			@RequestParam MultipartFile file, 
			@RequestParam String comment) {
		Image image = null;
		try {
			image = prepareImage(file, comment);
		} catch (IOException e) {
			e.printStackTrace();
			return "uncaughtException";
		}
		image = imageService.saveImage(image);
		return paramHttpServletRequest.getRequestURL() + image.getId();
	}
	
	private Image prepareImage(MultipartFile multipartFile, String comment) throws IOException {
		Image image = new Image();
		image.setBytes(multipartFile.getBytes());
		image.setComment(comment);
		image.setTimestamp(System.currentTimeMillis());
		return image;
	}

}
