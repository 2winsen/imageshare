package org.twi.imageshare.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twi.imageshare.entities.Image;
import org.twi.imageshare.repositories.ImageTemplateRepository;

@Service(value = ImageService.IMAGE_SERVICE_BEAN)
public class ImageService {

	public static final String IMAGE_SERVICE_BEAN = "imageService";

	@Autowired
	private ImageTemplateRepository imageTemplateRepository;

	public Image saveImage(Image image) {
		imageTemplateRepository.save(image);
		return image;
	}

	public Image getImageBytesAndContentTypeById(String id) {
		return imageTemplateRepository.getImageBytesAndContentTypeById(id);
	}

	public Image getImageMetadatabyId(String id) {
		return imageTemplateRepository.getImageMetadatabyId(id);
	}

	public void removeOldImagesIfNecessary() {
		imageTemplateRepository.removeOldImagesIfNecessary();
	}
	
	public void removeAll() {
		imageTemplateRepository.removeAll();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getDBStats() {
		return imageTemplateRepository.getDBStats().toMap();
	}

}
