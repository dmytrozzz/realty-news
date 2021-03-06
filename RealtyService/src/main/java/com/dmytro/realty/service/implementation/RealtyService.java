package com.dmytro.realty.service.implementation;

import com.dmytro.realty.data.repository.CriteriaRepository;
import com.dmytro.realty.data.repository.ProxyRepository;
import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.domain.Proxy;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.engine.RealtyEngine;
import com.dmytro.realty.engine.parser.RealtyUnparsebleException;
import com.dmytro.realty.service.IRealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
@Service("realtyService")
public class RealtyService implements IRealtyService {
    @Autowired
    private CriteriaRepository criteriaRepository;
    @Autowired
    private ProxyRepository proxyRepository;
    @Autowired
    private UserRepository userRepository;
    private RealtyEngine realtyEngine = new RealtyEngine();

    public static Proxy proxy;

    @Override
    public void searchRealty() {
        Iterable<RealtyCriteria> criterias = criteriaRepository.findAll();
        RealtyService.proxy = proxyRepository.getRandom();
        long start = System.currentTimeMillis();

        for (RealtyCriteria criteria : criterias) {
            List<RealtyUser> coolMan = new LinkedList<>();
            for (RealtyUser user : criteria.getUserCollection())
                if (user.enabled && user.payed)
                    coolMan.add(user);

            if (!coolMan.isEmpty()) {
                proxy.setTries(proxy.getTries() + 1);
                try {
                    realtyEngine.searchAndSubscribe(criteria, coolMan);
                } catch (RealtyUnparsebleException e) {
                    System.out.println(e.getMessage());
                    proxy.setFailures(proxy.getFailures() + 1);
                }
            }
        }
        long time = (System.currentTimeMillis() - start) / 1000;
        proxy.setSeconds((proxy.getSeconds() + (int) time) / 2);
        proxyRepository.save(proxy);
    }
}