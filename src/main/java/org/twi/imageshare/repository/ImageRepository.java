package org.twi.imageshare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.twi.imageshare.domainobject.Image;

public interface ImageRepository extends MongoRepository<Image, String> {

}
