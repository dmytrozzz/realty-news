package com.dmytro.realty.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.data.repository.CriteriaRepository;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.logic.engine.RealtyEngine;
import com.dmytro.realty.service.IRealtyService;

@Repository
@Transactional
@Service("realtyService")
public class RealtyService implements IRealtyService {
    @Autowired
    private CriteriaRepository criteriaRepository;

    private RealtyEngine realtyEngine = new RealtyEngine();

    @Override
    public void searchRealty() {
	Iterable<RealtyCriteria> criterias = criteriaRepository.findAll();
	
	for (RealtyCriteria criteria : criterias) {	    	    
	    realtyEngine.searchAndSubscribe(criteria, criteria.getUserCollection());
	}
    }
}