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

public class RealtyWizard implements Serializable {
	private User user = new User();
	private List<String> realtyTypeList = Arrays.asList("Room", "Appartment");
	private String realtyType;

	public List<String> getRealtyTypeList() {
		return realtyTypeList;
	}

	public void setRealtyTypeList(List<String> realtyTypeList) {
		this.realtyTypeList = realtyTypeList;
	}

	public String getRealtyType() {
		return realtyType;
	}

	public void setRealtyType(String realtyType) {
		this.realtyType = realtyType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
