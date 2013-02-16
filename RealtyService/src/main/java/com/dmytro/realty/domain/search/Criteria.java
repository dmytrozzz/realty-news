package com.dmytro.realty.domain.search;

import java.io.Serializable;

public class Criteria implements Serializable {	
	
	private RealtyUnit realtyUnit;
	private OperationType operationType;
	private Term term;
	
	private CriteriaParameters criteriaParameters;

	public RealtyUnit getRealtyUnit() {
		return realtyUnit;
	}

	public void setRealtyUnit(RealtyUnit realtyUnit) {
		this.realtyUnit = realtyUnit;
	}		

	public OperationType getOperationType() {
		return operationType;
	}	
	
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public CriteriaParameters getCriteriaParameters() {
		return criteriaParameters;
	}

	public void setCriteriaParameters(CriteriaParameters criteriaParameters) {
		this.criteriaParameters = criteriaParameters;
	}
}
