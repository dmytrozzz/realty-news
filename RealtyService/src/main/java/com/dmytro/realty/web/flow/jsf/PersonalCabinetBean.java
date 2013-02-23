package com.dmytro.realty.web.flow.jsf;

public class PersonalCabinetBean {
    private UserPreferencesBean preferences = new UserPreferencesBean();

    public UserPreferencesBean getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferencesBean preferences) {
        this.preferences = preferences;
    }    
}
