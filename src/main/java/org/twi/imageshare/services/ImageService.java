package org.twi.imageshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twi.imageshare.entities.Image;
import org.twi.imageshare.repositories.ImageRepository;

@Service(value=ImageService.IMAGE_SERVICE_BEAN)
public class ImageService {
	
	public static final String IMAGE_SERVICE_BEAN = "imageService";
	
	@Autowired
	private ImageRepository imageRepository;
	
	public Image saveImage(Image image) {
		imageRepository.save(image);
		return image;
	}
	
	public Image getImage(String id) {
		List<Image> tempResults = imageRepository.findById(id);
		Image result = null;
		if (tempResults.size() > 0) {
			result = tempResults.get(0);
		}
		return result;
	}
	
	public List<Image> getAllImages() {
		return imageRepository.findAll();		
	}

}
