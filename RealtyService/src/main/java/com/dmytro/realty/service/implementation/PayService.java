package com.dmytro.realty.service.implementation;

import java.security.Principal;

import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.data.repository.BillingRepository;
import com.dmytro.realty.domain.Billing;
import com.dmytro.realty.domain.Billing.BillingState;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.service.IPayService;
import com.dmytro.realty.service.moneymaker.LiqPayRequest;
import com.dmytro.realty.service.moneymaker.LiqPayResponse;
import com.dmytro.realty.web.security.RealtyUserDetails;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Service("payService")
public class PayService implements IPayService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BillingRepository billingRepository;

	@Override
	public LiqPayRequest createLiqPayBilling() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		RealtyUser user = null;

		if (principal instanceof RealtyUserDetails)
			user = ((RealtyUserDetails) principal).getRealtyUser();
		else if (principal instanceof RealtyUser)
			user = (RealtyUser) principal;

		Billing billing = null;
		if (user.getBilling() == null) {
			billing = Billing.createLiqPayBilling();
			billing.setUser(user);
			billing.setUniqueID(user.getId() + "");
			user.setBilling(billing);
			userRepository.save(user);
		} else {
			billing = user.getBilling();
		}

		return new LiqPayRequest(billing.getUniqueID());
	}

	@Override
	public void processLiqPayBilling(String xml) {
		try {
			LiqPayResponse response = new Persister().read(
					LiqPayResponse.class, xml);
			Billing billy = billingRepository.findByUniqueID(response
					.getBillyId());

			if (billy != null && billy.getStatus() == BillingState.NEW) {
				if (response.isSuccess()) {
					billy.setStatus(BillingState.PAYED);
					billy.getUser().setPayed(true);
					billy.getUser().setEnabled(true);
				} else if (response.isFailure())
					billy.setStatus(BillingState.FAILED);
				else
					billy.setStatus(BillingState.PROCESSING);

				billy.setTransactionID(response.getTransactionId());
				billy.getUser().setPhone(response.getSenderPhone());

				billingRepository.save(billy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}