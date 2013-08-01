package com.dmytro.realty.service.implementation;

import com.dmytro.realty.data.repository.CriteriaRepository;
import com.dmytro.realty.data.repository.ProxyRepository;
import com.dmytro.realty.data.repository.RealtyOfferRepository;
import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.domain.Proxy;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.engine.RealtyEngine;
import com.dmytro.realty.engine.parser.RealtyUnparsebleException;
import com.dmytro.realty.service.IRealtyService;
import com.dmytro.realty.service.ISendManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("realtyService")
public class RealtyService implements IRealtyService {
    public static Proxy proxy;
    @Autowired
    private CriteriaRepository criteriaRepository;
    @Autowired
    private ProxyRepository proxyRepository;
    @Autowired
    private RealtyOfferRepository offerRepository;
    @Autowired
    private UserRepository userService;
    @Autowired
    private ISendManService sendManService;
    private RealtyEngine realtyEngine = new RealtyEngine();

    @Override
    public void searchRealty() {
        System.out.println("Starting searching realty!");
        Iterable<RealtyCriteria> criteriaList = criteriaRepository.findAllPayedAndEnabled();
        //measure time for proxy job
        //RealtyService.proxy = proxyRepository.getRandom();
        //long start = System.currentTimeMillis();
        //proxy.setTries(proxy.getTries() + 1);

        for (RealtyCriteria criteria : criteriaList) {
            searchByCriteria(criteria);
        }
        offerRepository.refresh();

        //long time = (System.currentTimeMillis() - start) / 1000;
        //proxy.setSeconds((proxy.getSeconds() + (int) time) / 2);
        //proxyRepository.save(proxy);
    }

    public void searchByCriteria(RealtyCriteria criteria) {
        try {
            List<RealtyOffer> resultOffers = realtyEngine.searchByCriteria(criteria);
            offerRepository.save(resultOffers);
            List<String> subscribersList = userService.findSubscribers(criteria.getId());
            String[] subscribers = new String[subscribersList.size()];
            subscribersList.toArray(subscribers);
            System.out.println(Arrays.deepToString(subscribers));
            sendManService.sendNews(resultOffers, subscribers);
        } catch (RealtyUnparsebleException e) {
            System.out.println(e.getMessage());
            proxy.setFailures(proxy.getFailures() + 1);
        }
    }

//    public static void main(String[] args) throws InterruptedException, RealtyUnparsebleException, IOException {
//
//        RealtyCriteria criteria = criteria(
//                Product.Type.APARTMENT, 2000, 5000, 1, 3, Product.Operation.RENT);
//
//        RealtyUser user = new RealtyUser();
//        user.setEmail("d.zonov@ukr.net");
//
//        RealtyUser user2 = new RealtyUser();
//        user2.setEmail("domestic_demon@rambler.ru");
//
//        RealtyEngine engine = new RealtyEngine();
//        while (true) {
//            System.out.println("Lets go!");
//            engine.searchByCriteria(criteria,
//                    Arrays.asList(user));
//            Thread.sleep(90000);
//        }
//
//    }
}