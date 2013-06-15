package com.dmytro.realty.service.implementation;

import com.dmytro.realty.data.repository.CriteriaRepository;
import com.dmytro.realty.data.repository.ProxyRepository;
import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.domain.Proxy;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.engine.RealtyEngine;
import com.dmytro.realty.engine.parser.RealtyUnparsebleException;
import com.dmytro.realty.service.IRealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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

    @Override
    public void searchRealty() {
        Iterable<RealtyCriteria> criterias = criteriaRepository.findAll();
        Proxy proxy = proxyRepository.getRandom();
        System.setProperty("http.proxyHost", proxy.getAddress());
        System.setProperty("http.proxyPort", proxy.getPort() + "");

        for (RealtyCriteria criteria : criterias) {
            proxy.setTries(proxy.getTries() + 1);
//	    System.out.println("FIRST!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//	    List<Integer> ids = userRepository.findAllUserIds(criteria.getId());
//	    System.out.println("SECOND!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//	    List<RealtyUser> users = userRepository.findPayedAndEnabled(ids);
            try {
                realtyEngine.searchAndSubscribe(criteria, criteria.getUserCollection());
            } catch (RealtyUnparsebleException e) {
                e.printStackTrace();
                if (e.getCause() instanceof IOException) {
                    proxy.setFailures(proxy.getFailures() + 1);
                    proxy = proxyRepository.save(proxy);
                }
            }
        }
    }
}