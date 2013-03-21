package org.twi.imageshare.entities;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

/**
 * This pojo is persisted to mongoDB as document
 * @author vitalik
 *
 */
@Document
public class Image {

	private static final int MAX_COMMENT_LENGTH = 200;
	
	@Id
	private String id;
	
	@Size(max = MAX_COMMENT_LENGTH)
	private String comment;
	private long timestamp;
	private MultipartFile file;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
