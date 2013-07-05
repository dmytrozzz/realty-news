package com.dmytro.realty.web.flow.jsf;

import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserPreferencesBean implements Serializable {
    private RealtyUser user;
    private String emailToRestore;
    private String loginToRestore;

    public UserPreferencesBean() {
        user = new RealtyUser();
        addCriteria();
    }

    public UserPreferencesBean(RealtyUser user) {
        this.user = user;
    }

    public RealtyUser getUser() {
        return user;
    }

    public void setUser(RealtyUser user) {
        this.user = user;
    }

    public void addCriteria() {
        user.getCriteriaCollection().add(new RealtyCriteria());
    }

    public List<RealtyCriteria> getCriteriaList() {
        return new ArrayList<>(user.getCriteriaCollection());
    }

    public void save(ActionEvent actionEvent) {
        // Persist user - this is in realty-flow
        FacesMessage msg = new FacesMessage("OK!", "Дані успішно збережено: " + user.getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Product.Type[] getRealtyUnits() {
        return Product.Type.values();
    }

    public Product.Location[] getRealtyLocations() {
        return Product.Location.values();
    }

    public Product.Operation[] getOperationTypes(Product.Type realtyUnit) {
        if (realtyUnit == Product.Type.ROOM)
            return new Product.Operation[]{Product.Operation.RENT};
            //return new String[] { "RENT", "FARM_OUT", "LOOKING_PARTNER" };
        else
            return Product.Operation.values();
        //return new String[] { "SELL", "BUY", "EXCHANGE", "RENT", "FARM_OUT", "LOOKING_PARTNER" };
    }

    public String getServiceStatus() {
        return user.isEnabled() ? "SERVICE_ENABLED" : "SERVICE_DISABLED";
    }

    public String getRunButtonLabel() {
        return user.isEnabled() ? "STOP_SERVICE" : "RUN_SERVICE";
    }

    public String getPayedStatus() {
        return user.isPayed() ? "SERVICE_PAYED" : "SERVICE_UNPAYED";
    }

    public String getEmailToRestore() {
        return emailToRestore;
    }

    public void setEmailToRestore(String emailToRestore) {
        this.emailToRestore = emailToRestore;
    }

    public String getLoginToRestore() {
        return loginToRestore;
    }

    public void setLoginToRestore(String loginToRestore) {
        this.loginToRestore = loginToRestore;
    }
}
