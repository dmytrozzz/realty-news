package com.dmytro.realty.service.implementation;

import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.engine.SendMan;
import com.dmytro.realty.service.ISendManService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sendManService")
public class SendManService implements ISendManService {
    private SendMan sendMan = new SendMan();

    /**
     * Sends new offers to users
     *
     * @param newRealtyOffers list of new realty offers bigger then 0
     * @param emails          list of users, subscribed for this criteria
     */
    public void sendNews(List<RealtyOffer> newRealtyOffers,
                         String[] emails) {
        if (!newRealtyOffers.isEmpty() && emails.length > 0) {
            sendMan.createMessage(newRealtyOffers);
            sendMan.addRecipients(emails);
            sendMan.sendEmail();
        } else {
            SendMan.sendNoOffersMessage(emails);
        }
    }

    @Override
    public void sendHTMLMessage(String html, RealtyUser user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
