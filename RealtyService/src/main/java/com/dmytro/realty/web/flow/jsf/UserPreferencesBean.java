package com.dmytro.realty.web.flow.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;
import com.dmytro.realty.web.flow.jsf.buffer.CriteriaBean;

public class UserPreferencesBean implements Serializable {
    private RealtyUser user;
    private List<CriteriaBean> criteriaList = new ArrayList<>();

    public UserPreferencesBean() {
	user = new RealtyUser();
	addCriteria();
    }

    public UserPreferencesBean(RealtyUser user) {
	this.user = user;
	for (RealtyCriteria criteria : user.getCriteriaCollection())
	    criteriaList.add(new CriteriaBean(criteria));
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
	FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getLogin());
	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ProductType[] getRealtyUnits() {
	return ProductType.values();
    }

    public String[] getOperationTypes(ProductType realtyUnit) {
	if (realtyUnit == ProductType.ROOM)
	    return new String[] { "RENT", "FARM_OUT", "LOOKING_PARTNER" };
	else
	    return new String[] { "SELL", "BUY", "EXCHANGE", "RENT", "FARM_OUT", "LOOKING_PARTNER" };
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
