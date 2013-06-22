package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.Proxy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProxyRepository extends CrudRepository<Proxy, Long> {

    @Query(value = "select p.id, p.address, p.port, p.failures, p.tries, p.seconds from proxy p order by random() limit 1", nativeQuery = true)
    Proxy getRandom();
}
