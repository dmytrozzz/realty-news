package com.dmytro.realty.service;

import java.util.List;

import com.dmytro.realty.domain.FeedBack;

public interface IFeedBackService {
	
	void sendFeedBack(FeedBack feedBack);

	List<FeedBack> getGoodFeedBacks();
}
