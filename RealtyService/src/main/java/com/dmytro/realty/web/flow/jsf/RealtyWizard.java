package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;

import org.primefaces.event.FlowEvent;

public class RealtyWizard implements Serializable {
    private UserPreferencesBean preferencesBean = new UserPreferencesBean();    

    public UserPreferencesBean getPreferencesBean() {
	return preferencesBean;
    }

    public void setPreferencesBean(UserPreferencesBean preferencesBean) {
	this.preferencesBean = preferencesBean;
    }

    public String onFlowProcess(FlowEvent event) {
	// logger.info("Current wizard step:" + event.getOldStep());
	// logger.info("Next step:" + event.getNewStep());

	/*
	 * if(skip) { skip = false; //reset in case user goes back return
	 * "confirm"; } else {
	 */
	return event.getNewStep();
	// }
    }
}
