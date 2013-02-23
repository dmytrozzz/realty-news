package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;

public class PersonalCabinetBean implements Serializable{
    private UserPreferencesBean preferences = new UserPreferencesBean();

    public UserPreferencesBean getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferencesBean preferences) {
        this.preferences = preferences;
    }    
}
