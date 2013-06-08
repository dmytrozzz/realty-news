package com.dmytro.realty.web.flow.jsf;

import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.web.flow.jsf.buffer.CriteriaBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserPreferencesBean implements Serializable {
    private RealtyUser user;
    private List<CriteriaBean> criteriaList = new ArrayList<>();
    private boolean authorized = false;

    public UserPreferencesBean() {
        user = new RealtyUser();
        addCriteria();
    }

    public UserPreferencesBean(RealtyUser user) {
        this.user = user;
        for (RealtyCriteria criteria : user.getCriteriaCollection())
            criteriaList.add(new CriteriaBean(criteria));
        authorized = true;
    }

    public RealtyUser getUser() {
        return user;
    }

    public void setUser(RealtyUser user) {
        this.user = user;
    }

    public List<CriteriaBean> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<CriteriaBean> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public void addCriteria() {
        criteriaList.add(new CriteriaBean());
    }

    public void save(ActionEvent actionEvent) {
        // Persist user - this is in realty-flow
        FacesMessage msg = new FacesMessage("Successful", "Дані успішно збережено :" + user.getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Product.Type[] getRealtyUnits() {
        return Product.Type.values();
    }

    public Product.Location[] getRealtyLocations() {
        return Product.Location.values();
    }

    public boolean isAuthorized() {
        return authorized;
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
}
