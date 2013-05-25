package com.dmytro.realty.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.data.repository.BillingRepository;
import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.service.IPayService;

@Repository
@Transactional
@Service("payService")
public class PayService implements IPayService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BillingRepository billingRepository;

	@Override
	public String getPay() {
		// TODO Auto-generated method stub
		return null;
	}

}
