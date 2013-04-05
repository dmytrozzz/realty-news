package com.dmytro.realty.web.flow.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	private boolean authorized = false;

	private Map<String, String> themes;	

	public Map<String, String> getThemes() {
		return themes;
	}

	public void setThemes(Map<String, String> themes) {
		this.themes = themes;
	}

	public UserPreferencesBean() {
		user = new RealtyUser();
		addCriteria();

		themes = new TreeMap<String, String>();
		themes.put("Aristo", "aristo");
		themes.put("Black-Tie", "black-tie");
		themes.put("Blitzer", "blitzer");
		themes.put("Bluesky", "bluesky");
		themes.put("Casablanca", "casablanca");
		themes.put("Cupertino", "cupertino");
		themes.put("Dark-Hive", "dark-hive");
		themes.put("Dot-Luv", "dot-luv");
		themes.put("Eggplant", "eggplant");
		themes.put("Excite-Bike", "excite-bike");
		themes.put("Flick", "flick");
		themes.put("Glass-X", "glass-x");
		themes.put("Hot-Sneaks", "hot-sneaks");
		themes.put("Humanity", "humanity");
		themes.put("Le-Frog", "le-frog");
		themes.put("Midnight", "midnight");
		themes.put("Mint-Choc", "mint-choc");
		themes.put("Overcast", "overcast");
		themes.put("Pepper-Grinder", "pepper-grinder");
		themes.put("Redmond", "redmond");
		themes.put("Rocket", "rocket");
		themes.put("Sam", "sam");
		themes.put("Smoothness", "smoothness");
		themes.put("South-Street", "south-street");
		themes.put("Start", "start");
		themes.put("Sunny", "sunny");
		themes.put("Swanky-Purse", "swanky-purse");
		themes.put("Trontastic", "trontastic");
		themes.put("UI-Darkness", "ui-darkness");
		themes.put("UI-Lightness", "ui-lightness");
		themes.put("Vader", "vader");
	}

	public UserPreferencesBean(RealtyUser user) {
		this.user = user;
		for (RealtyCriteria criteria : user.getCriteriaCollection())
			criteriaList.add(new CriteriaBean(criteria));
		authorized = true;
		
		themes = new TreeMap<String, String>();
		themes.put("Aristo", "aristo");
		themes.put("Black-Tie", "black-tie");
		themes.put("Blitzer", "blitzer");
		themes.put("Bluesky", "bluesky");
		themes.put("Casablanca", "casablanca");
		themes.put("Cupertino", "cupertino");
		themes.put("Dark-Hive", "dark-hive");
		themes.put("Dot-Luv", "dot-luv");
		themes.put("Eggplant", "eggplant");
		themes.put("Excite-Bike", "excite-bike");
		themes.put("Flick", "flick");
		themes.put("Glass-X", "glass-x");
		themes.put("Hot-Sneaks", "hot-sneaks");
		themes.put("Humanity", "humanity");
		themes.put("Le-Frog", "le-frog");
		themes.put("Midnight", "midnight");
		themes.put("Mint-Choc", "mint-choc");
		themes.put("Overcast", "overcast");
		themes.put("Pepper-Grinder", "pepper-grinder");
		themes.put("Redmond", "redmond");
		themes.put("Rocket", "rocket");
		themes.put("Sam", "sam");
		themes.put("Smoothness", "smoothness");
		themes.put("South-Street", "south-street");
		themes.put("Start", "start");
		themes.put("Sunny", "sunny");
		themes.put("Swanky-Purse", "swanky-purse");
		themes.put("Trontastic", "trontastic");
		themes.put("UI-Darkness", "ui-darkness");
		themes.put("UI-Lightness", "ui-lightness");
		themes.put("Vader", "vader");
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

	public boolean isAuthorized() {
		return authorized;
	}

	public String[] getOperationTypes(ProductType realtyUnit) {
		if (realtyUnit == ProductType.ROOM)
			return new String[] { "RENT", "FARM_OUT", "LOOKING_PARTNER" };
		else
			return new String[] { "SELL", "BUY", "EXCHANGE", "RENT", "FARM_OUT", "LOOKING_PARTNER" };
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
