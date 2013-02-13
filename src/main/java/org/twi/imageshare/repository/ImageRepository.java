package org.twi.imageshare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.twi.imageshare.domainobject.Image;

public class ImageRepository {

	@Autowired
	public MongoTemplate mongoTemplate;
	
	public void insertImage(Image image) {
	}
	
}
