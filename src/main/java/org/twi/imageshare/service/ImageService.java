package org.twi.imageshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twi.imageshare.domainobject.Image;
import org.twi.imageshare.repository.ImageRepository;

@Service("imageService")
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	public void saveImage(Image image) {
		imageRepository.save(image);
	}
	
	public List<Image> getAllImages() {
		return imageRepository.findAll();		
	}

}
