package com.dmytro.realty.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.data.repository.FeedBackRepository;
import com.dmytro.realty.domain.FeedBack;
import com.dmytro.realty.service.IFeedBackService;

@Repository
@Transactional
@Service("feedBackService")
public class FeedBackService implements IFeedBackService {
	@Autowired
	private FeedBackRepository feedBackRepository;

	@Override
	public void sendFeedBack(FeedBack feedBack) {
		feedBackRepository.save(feedBack);
	}

	@Override
	public List<FeedBack> getGoodFeedBacks() {
		return feedBackRepository.findAllByGood(true);
	}
}
