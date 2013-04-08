package org.twi.imageshare.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.twi.imageshare.entities.Image;

import com.mongodb.CommandResult;
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

	public Image getImageBytesAndContentTypeById(String id) {
		Query query = new Query();
		query.fields().include(Image.FIELD_NAME_BYTES);
		query.fields().include(Image.FIELD_NAME_CONTENT_TYPE);
		query.addCriteria(Criteria.where(Image.FIELD_NAME_ID).is(id));
		return mongoTemplate.findOne(query, Image.class);
	}

	public Image getImageMetadatabyId(String id) {
		Query query = new Query();
		query.fields().include(Image.FIELD_NAME_TIMESTAMP);
		query.fields().include(Image.FIELD_NAME_COMMENT);
		query.addCriteria(Criteria.where(Image.FIELD_NAME_ID).is(id));
		return mongoTemplate.findOne(query, Image.class);
	}

	public CommandResult getDBStats() {
		return db.getStats();
	}

	private boolean isMaxAllowedSpaceInUse() {
		int size = (Integer) getDBStats().get("dataSize");
		return size >= MAX_ALLOWED_DB_SIZE;
	}

}
