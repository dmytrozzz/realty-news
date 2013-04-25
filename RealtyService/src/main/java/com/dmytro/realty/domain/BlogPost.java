package com.dmytro.realty.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "blogpost")
public class BlogPost implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_seq")
	@SequenceGenerator(name = "blog_seq", sequenceName = "blogpost_id_seq", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "author")
	private String author;

	@Column(name = "title")
	private String title;

	@Column(name = "foreword")
	private String foreword;

	@Column(name = "content")
	private String content;

	@Column(name = "picture")
	private String picture;

	@Column(name = "suffix")
	private String suffix;

	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;

	@PrePersist
	void insert() {
		this.postDate = new Date();
	}

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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForeword() {
		return foreword;
	}

	public void setForeword(String foreword) {
		this.foreword = foreword;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getBreakedContent() {
		return content.replace("\n\r", "<br />").replace("\n", "<br />")
				.replace("\r", "<br />");
	}
}
