package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import com.dmytro.realty.domain.User;
import com.dmytro.realty.domain.search.Criteria;
import com.dmytro.realty.domain.search.OperationType;
import com.dmytro.realty.domain.search.RealtyUnit;

public class RealtyWizard implements Serializable {
	private User user;
	private List<Criteria> criteriaList;
	private Criteria selectedCriteria;

	public RealtyWizard() {
		user = new User();
		criteriaList = new ArrayList<>();
		criteriaList.add(new Criteria());
	}

	public List<Criteria> getCriteriaList() {
		return criteriaList;
	}

	public void setCriteriaList(List<Criteria> criteriaList) {
		this.criteriaList = criteriaList;
	}

	public Criteria getSelectedCriteria() {
		return selectedCriteria;
	}

	public void setSelectedCriteria(Criteria selectedCriteria) {
		this.selectedCriteria = selectedCriteria;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addCriteria() {
		criteriaList.add(new Criteria());
	}

	public RealtyUnit[] getRealtyUnits() {
		return RealtyUnit.values();
	}

	public OperationType[] getOperationTypes(RealtyUnit realtyUnit) {
		if (realtyUnit == RealtyUnit.ROOM)
			return new OperationType[] { OperationType.RENT,
					OperationType.FARM_OUT, OperationType.LOOKING_PARTNER };
		else
			return OperationType.values();
	}

	public void save(ActionEvent actionEvent) {
		// Persist user
		FacesMessage msg = new FacesMessage("Successful", "Welcome :"
				+ user.getLogin());
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
