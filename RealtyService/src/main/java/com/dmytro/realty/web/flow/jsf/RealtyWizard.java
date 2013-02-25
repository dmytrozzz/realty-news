package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;

import org.primefaces.event.FlowEvent;

public class RealtyWizard implements Serializable {
    private UserPreferencesBean preferences;

    public RealtyWizard(UserPreferencesBean preferences) {
	this.preferences = preferences;
    }

    public UserPreferencesBean getPreferences() {
	return preferences;
    }

    public void setPreferences(UserPreferencesBean preferences) {
	this.preferences = preferences;
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
