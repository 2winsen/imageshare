package org.twi.imageshare.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.twi.imageshare.entities.Image;

import com.mongodb.DB;

@Repository
public class ImageTemplateRepository {

	public static final int MAX_ALLOWED_DB_SIZE = 94371840; // 90 MB in bytes
	public static final int MAX_DELETE_LIMIT = 15;

	private MongoTemplate mongoTemplate;

	private DB db;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		db = mongoTemplate.getDb();
	}

	public void removeOldImagesIfNecessary() {
		if (isMaxAllowedSpaceInUse()) {
			mongoTemplate.findAndRemove(new Query().limit(MAX_DELETE_LIMIT), Image.class);
		}
	}

	private boolean isMaxAllowedSpaceInUse() {
		int size = (Integer) db.getStats().get("dataSize");
		return size >= MAX_ALLOWED_DB_SIZE;
	}

}
