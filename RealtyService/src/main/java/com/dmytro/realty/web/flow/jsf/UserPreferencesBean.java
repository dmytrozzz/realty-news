package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.dmytro.realty.domain.User;
import com.dmytro.realty.domain.search.SearchCriteria;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

public class UserPreferencesBean implements Serializable {
    private User user;
    private Collection<SearchCriteria> criteriaList;  
    
    public UserPreferencesBean() {
	user = new User();
	criteriaList = new LinkedList<>();
	addCriteria();
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }    

    public Collection<SearchCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(Collection<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }   

    public void addCriteria() {
	criteriaList.add(new SearchCriteria());
    }

    public void save(ActionEvent actionEvent) {
	// Persist user - this is in realty-flow
	FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getLogin());
	FacesContext.getCurrentInstance().addMessage(null, msg);
    }              

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
