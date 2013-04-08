package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BlogBean implements Serializable {

	private List<Article> articles;

	public BlogBean(List<Article> articles) {
		// this.articles = articles;
		this.articles = new LinkedList<>();
		this.articles.add(new Article("Its a sunny day", "Dmytro", "01.01.10", "It was sunny day",
				"It was very sunny day", "it-was-sunny-day"));
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public static class Article implements Serializable {
		private String title;
		private String author;
		private String date;
		private String preContent;
		private String content;
		private String coolRef;

		public Article(String title, String author, String date, String preContent, String content, String coolRef) {
			this.title = title;
			this.author = author;
			this.date = date;
			this.preContent = preContent;
			this.content = content;
			this.coolRef = coolRef;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getPreContent() {
			return preContent;
		}

		public void setPreContent(String preContent) {
			this.preContent = preContent;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getCoolRef() {
			return coolRef;
		}

		public void setCoolRef(String coolRef) {
			this.coolRef = coolRef;
		}

		public String getBreakedContent() {
			return content.replace("\n", "<br />");
		}
		
		public String getBreakedPreContent() {
			return preContent.replace("\n", "<br />");
		}
	}

}
