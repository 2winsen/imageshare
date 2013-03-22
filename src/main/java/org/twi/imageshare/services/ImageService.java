package org.twi.imageshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twi.imageshare.entities.Image;
import org.twi.imageshare.repositories.ImageSpringDataRepository;
import org.twi.imageshare.repositories.ImageTemplateRepository;

@Service(value = ImageService.IMAGE_SERVICE_BEAN)
public class ImageService {

	public static final String IMAGE_SERVICE_BEAN = "imageService";

	@Autowired
	private ImageSpringDataRepository springDataRepository;

	@Autowired
	private ImageTemplateRepository templateRepository;

	public Image saveImage(Image image) {
		springDataRepository.save(image);
		return image;
	}

	public Image getImage(String id) {
		List<Image> tempResults = springDataRepository.findById(id);
		Image result = null;
		if (tempResults.size() > 0) {
			result = tempResults.get(0);
		}
		return result;
	}

	public List<Image> getAllImages() {
		return springDataRepository.findAll();
	}

	public void removeOldImagesIfNecessary() {
		templateRepository.removeOldImagesIfNecessary();
	}

}
