package com.dmytro.realty.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.FeedBack;
import com.dmytro.realty.service.IFeedBackService;

@Controller("feedBackController")
public class FeedBackController {
	@Autowired
	private IFeedBackService feedBackService;
	
	public FeedBackBean createFullFeedBackBean(){
		FeedBackBean fb = new FeedBackBean();
		fb.feedBacks = feedBackService.getGoodFeedBacks();
		return fb;
	}
	
	public FeedBackBean createLightFeedBackBean(){
		FeedBackBean fb = new FeedBackBean();
		fb.feedBack = new FeedBack();
		return fb;
	}

	public void postFeedBack(FeedBack feedBack) {
		feedBackService.sendFeedBack(feedBack);
	}

	public static class FeedBackBean implements Serializable {
		private FeedBack feedBack;
		private List<FeedBack> feedBacks;

		public FeedBack getFeedBack() {
			return feedBack;
		}

		public void setFeedBack(FeedBack feedBack) {
			this.feedBack = feedBack;
		}

		public List<FeedBack> getFeedBacks() {
			return feedBacks;
		}

		public void setFeedBacks(List<FeedBack> feedBacks) {
			this.feedBacks = feedBacks;
		}
	}
}
