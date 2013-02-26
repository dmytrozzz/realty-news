package com.dmytro.realty.web.flow.jsf;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class UserLoginBean {
    private String username;
    private String password;

    public UserLoginBean() {
	super();
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
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
