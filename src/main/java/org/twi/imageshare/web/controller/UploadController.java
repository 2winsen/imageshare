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
 * MVC Controller for uploading Images (default page)
 * @author vitalijs.sakels
 *
 */
@Controller
@RequestMapping("/")
public class UploadController {

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService; 
	
	/**
	 * Rendering index page
	 * @param model
	 * @return
	 */	
	@RequestMapping(method = RequestMethod.GET)
	public String indexView() {
		return "index";
	}
	
	/**
	 * AJAX Called once user is submitting upload form
	 * @param model
	 * @param file - Uploaded file
	 * @param comment - Comment for uploaded file
	 * @return
	 */
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
	
	/**
	 * Making Image object for persistence
	 * @param multipartFile
	 * @param comment
	 * @return
	 * @throws IOException
	 */
	private Image prepareImage(MultipartFile multipartFile, String comment) throws IOException {
		Image image = new Image();
		image.setBytes(multipartFile.getBytes());
		image.setComment(comment);
		image.setTimestamp(System.currentTimeMillis());
		return image;
	}

}
