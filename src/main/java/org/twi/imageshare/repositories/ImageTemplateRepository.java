package org.twi.imageshare.repositories;

import java.util.List;

import org.joda.time.DateTime;
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
	public static final int MAX_DELETE_LIMIT = 25;

	public static final int DAYS_LIMIT = 2;

	private MongoTemplate mongoImageTemplate;

	private DB db;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoImageTemplate = mongoTemplate;
		db = mongoTemplate.getDb();
	}

	public void save(Image image) {
		mongoImageTemplate.save(image);
	}

	public void removeOldImagesIfNecessary() {
		removeOldImagesIfDatabaseIsFull();
		removeOldImagesAfter2DaysPeriod();
	}

	public void removeOldImagesIfDatabaseIsFull() {
		if (isMaxAllowedSpaceInUse()) {
			mongoImageTemplate.findAndRemove(new Query().limit(MAX_DELETE_LIMIT), Image.class);
		}
	}

	public void removeOldImagesAfter2DaysPeriod() {
		List<Image> images = getAllImagesTimestamps();
		for (Image image : images) {
			DateTime date = new DateTime(image.getTimestamp());
			if (date.plusDays(DAYS_LIMIT).isBeforeNow()) {
				mongoImageTemplate.remove(image);
			}
		}
	}
	
	public void removeAll() {
		mongoImageTemplate.remove(new Query(), Image.class);
	}

	public Image getImageBytesAndContentTypeById(String id) {
		Query query = new Query();
		query.fields().include(Image.FIELD_NAME_BYTES);
		query.fields().include(Image.FIELD_NAME_CONTENT_TYPE);
		query.addCriteria(Criteria.where(Image.FIELD_NAME_ID).is(id));
		return mongoImageTemplate.findOne(query, Image.class);
	}

	public Image getImageMetadatabyId(String id) {
		Query query = new Query();
		query.fields().include(Image.FIELD_NAME_TIMESTAMP);
		query.fields().include(Image.FIELD_NAME_COMMENT);
		query.addCriteria(Criteria.where(Image.FIELD_NAME_ID).is(id));
		return mongoImageTemplate.findOne(query, Image.class);
	}

	public List<Image> getAllImagesTimestamps() {
		Query query = new Query();
		query.fields().include(Image.FIELD_NAME_TIMESTAMP);
		return mongoImageTemplate.find(query, Image.class);
	}

	public CommandResult getDBStats() {
		return db.getStats();
	}

	private boolean isMaxAllowedSpaceInUse() {
        int size = ((Double)getDBStats().get("dataSize")).intValue();
		return size >= MAX_ALLOWED_DB_SIZE;
	}

}
