package com.dmytro.realty.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class FeedBack implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
	@SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_id_seq", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "author")
	private String author;

	@Column(name = "content")
	private String content;
	
	@Column(name = "good")
	private boolean good;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}
	
	
}
