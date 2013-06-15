package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.Proxy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 15.06.13
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public interface ProxyRepository extends CrudRepository<Proxy, Long> {

    @Query(value = "select p from proxy p order by random() limit 1",nativeQuery = true)
    public Proxy getRandom();
}
