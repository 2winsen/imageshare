package org.twi.imageshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twi.imageshare.repository.ImageRepository;

@Service("imageService")
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;

}
