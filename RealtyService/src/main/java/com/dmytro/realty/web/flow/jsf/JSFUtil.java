package com.dmytro.realty.web.flow.jsf;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import org.primefaces.util.Constants;

public class JSFUtil implements Serializable {
    
    public void initalizeContext() {
	FacesContext context = FacesContext.getCurrentInstance();
	RequestContext requestContext = ((RequestContext) context.getAttributes().get(Constants.REQUEST_CONTEXT_ATTR));
	if (requestContext == null) {
	    context.getAttributes().put(Constants.REQUEST_CONTEXT_ATTR, new DefaultRequestContext());
	}
    }
}
