package org.twi.imageshare.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.twi.imageshare.domainobject.Image;

public class ImageRepository {

	public static final Logger logger = LoggerFactory.getLogger(ImageRepository.class);
	
	@Autowired
	public MongoTemplate mongoTemplate;
	
	public void insertImage(Image image) {
	}
	
}
