package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class FeedBackBeen implements Serializable {

	private List<FeedBack> feedBacks;

	public FeedBackBeen(List<FeedBack> feedbacks) {
		this.feedBacks = new LinkedList<>();
		this.feedBacks.add(new FeedBack("Dmytro", "Very cool service"));
		this.feedBacks.add(new FeedBack("Sancho", "It'd very helped me! Recommend!"));
	}

	public List<FeedBack> getFeedBacks() {
		return feedBacks;
	}

	public void setFeedBacks(List<FeedBack> feedBacks) {
		this.feedBacks = feedBacks;
	}

	public static class FeedBack implements Serializable {
		private String author;
		private String content;

		public FeedBack(String author, String content) {
			this.author = author;
			this.content = content;
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
	}
}
