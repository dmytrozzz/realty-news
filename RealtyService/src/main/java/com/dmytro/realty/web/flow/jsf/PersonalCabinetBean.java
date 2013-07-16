package com.dmytro.realty.web.flow.jsf;

import com.dmytro.realty.domain.RealtyOffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonalCabinetBean implements Serializable {
    private UserPreferencesBean preferences;

    private List<RealtyOffer> realtyOffers;

    public PersonalCabinetBean(UserPreferencesBean preferences) {
        this.preferences = preferences;
    }

    public UserPreferencesBean getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferencesBean preferences) {
        this.preferences = preferences;
    }

    public List<RealtyOffer> getRealtyOffers() {
        return realtyOffers;
    }

    public void setRealtyOffers(List<RealtyOffer> realtyOffers) {
        this.realtyOffers = realtyOffers;
    }

    public List<RealtyOffer> getFavouriteOffers() {
        return new ArrayList<>(preferences.getUser().getFavouriteOffers());
    }

    public void makeFavour(RealtyOffer offer) {
        preferences.getUser().getFavouriteOffers().add(offer);
    }

    public void unFavour(RealtyOffer offer) {
        preferences.getUser().getFavouriteOffers().remove(offer);
    }

    public boolean isFavour(RealtyOffer offer) {
        return preferences.getUser().getFavouriteOffers().contains(offer);
    }
}
