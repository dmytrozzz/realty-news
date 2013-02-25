package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;

public class PersonalCabinetBean implements Serializable{
    private UserPreferencesBean preferences;
    
    public PersonalCabinetBean(UserPreferencesBean preferences) {
	this.preferences = preferences;
    }

    public UserPreferencesBean getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferencesBean preferences) {
        this.preferences = preferences;
    }    
}
