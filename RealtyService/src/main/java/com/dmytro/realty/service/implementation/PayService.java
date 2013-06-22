package com.dmytro.realty.service.implementation;

import com.dmytro.realty.data.repository.BillingRepository;
import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.domain.Billing;
import com.dmytro.realty.domain.Billing.BillingService;
import com.dmytro.realty.domain.Billing.BillingState;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.service.IPayService;
import com.dmytro.realty.service.moneymaker.LiqPayRequest;
import com.dmytro.realty.service.moneymaker.LiqPayResponse;
import com.dmytro.realty.web.security.RealtyUserDetails;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.dmytro.realty.domain.Billing.BillingState.FAILED;
import static com.dmytro.realty.domain.Billing.BillingState.NEW;
import static com.dmytro.realty.domain.Billing.BillingState.PROCESSING;

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
        Billing billing = getBilly(BillingService.LIQPAY);

        return new LiqPayRequest(billing.getId() + "");
    }

    @Override
    public void processLiqPayBilling(String xml) {
        try {
            LiqPayResponse response = new Persister().read(
                    LiqPayResponse.class, xml);
            Billing billy = billingRepository.findByUniqueID(response.getBillyId());

            if (billy != null && Arrays.asList(NEW, PROCESSING).contains(billy.getStatus())) {
                if (response.isSuccess()) {
                    billy.setStatus(BillingState.PAYED);
                    billy.getUser().setPayed(true);
                    billy.getUser().setEnabled(true);
                } else if (response.isFailure())
                    billy.setStatus(FAILED);
                else
                    billy.setStatus(PROCESSING);

                billy.setTransactionID(response.getTransactionId());
                billy.getUser().setPhone(response.getSenderPhone());

                billingRepository.save(billy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processEasyPayBilling(String orderId, int amount, String comission) {
        System.out.println("Was here ----" + orderId+"---"+amount+"---"+comission);
        Billing billy = billingRepository.findByUniqueID(orderId);

        if (billy != null && Arrays.asList(NEW, PROCESSING).contains(billy.getStatus())) {
            billy.setStatus(BillingState.PAYED);
            billy.getUser().setPayed(true);
            billy.getUser().setEnabled(true);
        }
        billingRepository.save(billy);
    }

    @Override
    public Billing createEasyPayBilling() {
        return getBilly(BillingService.EASYPAY);
    }

    private Billing getBilly(BillingService service) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        RealtyUser user = null;

        if (principal instanceof RealtyUserDetails)
            user = ((RealtyUserDetails) principal).getRealtyUser();
        else if (principal instanceof RealtyUser)
            user = (RealtyUser) principal;

        Billing billing = null;
        if (user != null) {
            billing = user.getBilling();
        }

        if (billing == null || Arrays.asList(FAILED, NEW).contains(billing.getStatus())) {
            billing = billingRepository.save(Billing.createBilling(service));
            billing.setUniqueID(billing.getId() + "");
            billing.setUser(user);
            billing = billingRepository.save(billing);
        }
        return billing;
    }
}