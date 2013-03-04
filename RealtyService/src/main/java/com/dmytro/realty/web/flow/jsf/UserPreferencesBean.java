package com.dmytro.realty.web.flow.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.SearchCriteria;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

public class UserPreferencesBean implements Serializable {
    private RealtyUser user;
    private Collection<SearchCriteria> criteriaList;

    public UserPreferencesBean() {
	user = new RealtyUser();
	criteriaList = new LinkedList<>();
	addCriteria();
    }

    public RealtyUser getUser() {
	return user;
    }

    public void setUser(RealtyUser user) {
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

    public String login() throws ServletException, IOException {
	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	HttpServletRequest request = ((HttpServletRequest) context.getRequest());

	ServletResponse resposnse = ((ServletResponse) context.getResponse());
	RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
	dispatcher.forward(request, resposnse);
	FacesContext.getCurrentInstance().responseComplete();

	return null;
    }
}
