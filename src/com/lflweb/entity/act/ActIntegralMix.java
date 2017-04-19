package com.lflweb.entity.act;

import java.util.List;

public class ActIntegralMix{
	private ActIntegral actIntegral;
	
	private List<ActIntegralMonth> actIntegralMonths;
	
	private List<ActIntegralDetail> actIntegralDetails;

	public ActIntegral getActIntegral() {
		return actIntegral;
	}

	public void setActIntegral(ActIntegral actIntegral) {
		this.actIntegral = actIntegral;
	}

	public List<ActIntegralMonth> getActIntegralMonths() {
		return actIntegralMonths;
	}

	public void setActIntegralMonths(List<ActIntegralMonth> actIntegralMonths) {
		this.actIntegralMonths = actIntegralMonths;
	}

	public List<ActIntegralDetail> getActIntegralDetails() {
		return actIntegralDetails;
	}

	public void setActIntegralDetails(List<ActIntegralDetail> actIntegralDetails) {
		this.actIntegralDetails = actIntegralDetails;
	}
	
	
	
	
}