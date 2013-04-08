package org.twi.imageshare.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.twi.imageshare.entities.Image;

/**
 * Spring data for MongoDB
 * @author vitalik
 *
 */
public interface ImageSpringDataRepository extends MongoRepository<Image, String> {

}
