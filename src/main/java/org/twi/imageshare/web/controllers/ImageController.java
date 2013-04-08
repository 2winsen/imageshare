package org.twi.imageshare.web.controllers;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.twi.imageshare.entities.Image;
import org.twi.imageshare.services.ImageService;

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
	public String imagePage(@PathVariable String imageId, Model model, HttpServletRequest paramHttpServletRequest) {
		Image image = imageService.getImageMetadatabyId(imageId);
		model.addAttribute("comment", image.getComment());
		model.addAttribute("url", paramHttpServletRequest.getRequestURL());
		model.addAttribute("date", new Date(image.getTimestamp()));
		return "image";
	}

	@RequestMapping(value = "/image_src/{imageId}", method = RequestMethod.GET)
	public void imageSrc(@PathVariable String imageId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Image image = imageService.getImageBytesAndContentTypeById(imageId);
		response.setContentType(image.getContentType());
		ServletOutputStream out = response.getOutputStream();
		out.write(image.getBytes());
		out.flush();
	}

}
