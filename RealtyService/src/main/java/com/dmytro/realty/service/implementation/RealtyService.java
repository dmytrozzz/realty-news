package com.dmytro.realty.service.implementation;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.data.repository.CriteriaRepository;
import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.engine.RealtyEngine;
import com.dmytro.realty.service.IRealtyService;

@Repository
@Transactional
@Service("realtyService")
public class RealtyService implements IRealtyService {
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Autowired
    private UserRepository userRepository;

    private RealtyEngine realtyEngine = new RealtyEngine();

    @Override
    public void searchRealty() {
	Iterable<RealtyCriteria> criterias = criteriaRepository.findAll();

	for (RealtyCriteria criteria : criterias) {
//	    System.out.println("FIRST!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//	    List<Integer> ids = userRepository.findAllUserIds(criteria.getId());
//	    System.out.println("SECOND!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//	    List<RealtyUser> users = userRepository.findPayedAndEnabled(ids);
	    realtyEngine.searchAndSubscribe(criteria, criteria.getUserCollection());
	}
    }
}