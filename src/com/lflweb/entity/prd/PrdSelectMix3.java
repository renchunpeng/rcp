package com.lflweb.entity.prd;

import java.util.ArrayList;
import java.util.List;

public class PrdSelectMix3{

	private List<PrdTypeSelect> prdBases1;
	private List<PrdTypeSelect> prdBases2;
	private List<PrdTypeSelect> prdBases3;
	private List<PrdTypeSelect> prdBases4;
	private List<PrdTypeSelect> prdBases5;
	private List<PrdTypeSelect> prdBases6;
	private List<PrdTypeSelect> prdBases7;

	public PrdSelectMix3() {
		this.OnInit();
	}
	
	public void OnInit() {
		this.prdBases1 = new ArrayList<PrdTypeSelect>();
		this.prdBases2 = new ArrayList<PrdTypeSelect>();
		this.prdBases3 = new ArrayList<PrdTypeSelect>();
		this.prdBases4 = new ArrayList<PrdTypeSelect>();
		this.prdBases5 = new ArrayList<PrdTypeSelect>();
		this.prdBases6 = new ArrayList<PrdTypeSelect>();
		this.prdBases7 = new ArrayList<PrdTypeSelect>();
	}

	public List<PrdTypeSelect> getPrdBases1() {
		return prdBases1;
	}

	public void setPrdBases1(List<PrdTypeSelect> prdBases1) {
		this.prdBases1 = prdBases1;
	}

	public List<PrdTypeSelect> getPrdBases2() {
		return prdBases2;
	}

	public void setPrdBases2(List<PrdTypeSelect> prdBases2) {
		this.prdBases2 = prdBases2;
	}

	public List<PrdTypeSelect> getPrdBases3() {
		return prdBases3;
	}

	public void setPrdBases3(List<PrdTypeSelect> prdBases3) {
		this.prdBases3 = prdBases3;
	}

	public List<PrdTypeSelect> getPrdBases4() {
		return prdBases4;
	}

	public void setPrdBases4(List<PrdTypeSelect> prdBases4) {
		this.prdBases4 = prdBases4;
	}

	public List<PrdTypeSelect> getPrdBases5() {
		return prdBases5;
	}

	public void setPrdBases5(List<PrdTypeSelect> prdBases5) {
		this.prdBases5 = prdBases5;
	}

	public List<PrdTypeSelect> getPrdBases6() {
		return prdBases6;
	}

	public void setPrdBases6(List<PrdTypeSelect> prdBases6) {
		this.prdBases6 = prdBases6;
	}

	public List<PrdTypeSelect> getPrdBases7() {
		return prdBases7;
	}

	public void setPrdBases7(List<PrdTypeSelect> prdBases7) {
		this.prdBases7 = prdBases7;
	}

	
	
	
}