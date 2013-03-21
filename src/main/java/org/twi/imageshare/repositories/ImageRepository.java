package org.twi.imageshare.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.twi.imageshare.entities.Image;

/**
 * Spring data for MongoDB
 * @author vitalik
 *
 */
public interface ImageRepository extends MongoRepository<Image, String> {

	/**
	 * Get image document by ID (using spring data)
	 * @param id
	 * @return
	 */
	List<Image> findById(String id);

}
