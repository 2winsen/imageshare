package org.twi.imageshare.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.twi.imageshare.domainobject.Image;

public interface ImageRepository extends MongoRepository<Image, String> {

	/**
	 * Get image document by ID (using spring data)
	 * @param id
	 * @return
	 */
	List<Image> findById(String id);

}
