package org.twi.imageshare.services;

import java.util.List;
import java.util.Map;

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

	public Image getImageBytesAndContentTypeById(String id) {
		return templateRepository.getImageBytesAndContentTypeById(id);
	}

	public Image getImageMetadatabyId(String id) {
		return templateRepository.getImageMetadatabyId(id);
	}

	public List<Image> getAllImages() {
		return springDataRepository.findAll();
	}

	public void removeOldImagesIfNecessary() {
		templateRepository.removeOldImagesIfNecessary();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getDBStats() {
		return templateRepository.getDBStats().toMap();
	}

}
