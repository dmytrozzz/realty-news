package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import com.dmytro.realty.domain.User;
import com.dmytro.realty.domain.search.Category;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

public class RealtyWizard implements Serializable {
    private User user;
    private List<Category> criteriaList;
    private Category selectedCriteria;

    public RealtyWizard() {
	user = new User();
	criteriaList = new ArrayList<>();
	criteriaList.add(new Category());
    }

    public List<Category> getCriteriaList() {
	return criteriaList;
    }

    public void setCriteriaList(List<Category> criteriaList) {
	this.criteriaList = criteriaList;
    }

    public Category getSelectedCriteria() {
	return selectedCriteria;
    }

    public void setSelectedCriteria(Category selectedCriteria) {
	this.selectedCriteria = selectedCriteria;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public void addCriteria() {
	criteriaList.add(new Category());
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

    public void save(ActionEvent actionEvent) {
	// Persist user - this is in realty-flow
	FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getLogin());
	FacesContext.getCurrentInstance().addMessage(null, msg);
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
