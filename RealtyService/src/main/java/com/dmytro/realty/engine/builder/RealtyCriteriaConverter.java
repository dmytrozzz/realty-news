package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.RealtyCriteria;

public interface RealtyCriteriaConverter {
    String protocol = "http://";

    String buildRequest(RealtyCriteria criteria);
}