package com.dmytro.realty.web.flow.jsf;

import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

public final class GlobalPreferencesBean {
    //TODO be application scope - to prevent copying in each user flow or session or etc.
    
    public ProductType[] getRealtyUnits() {
	return ProductType.values();
    }

    public OperationType[] getOperationTypes(ProductType realtyUnit) {
	if (realtyUnit == ProductType.ROOM)
	    return new OperationType[] { OperationType.RENT, OperationType.FARM_OUT, OperationType.LOOKING_PARTNER };
	else
	    return OperationType.values();
    }
}
