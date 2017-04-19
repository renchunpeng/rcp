package com.lflweb.entity.prd;

import java.util.ArrayList;
import java.util.List;

public class PrdSelectMix2{
	
	private List<PrdPackage> prdPackages;
	private List<PrdBase> prdBases1;
	private List<PrdBase> prdBases2;
	private List<PrdBase> prdBases3;
	private List<PrdBase> prdBases4;
	private List<PrdBase> prdBases5;
	private List<PrdBase> prdBases6;
	private List<PrdBase> prdBases7;

	public PrdSelectMix2() {
		this.OnInit();
	}
	
	public void OnInit() {
		this.prdBases1 = new ArrayList<PrdBase>();
		this.prdBases2 = new ArrayList<PrdBase>();
		this.prdBases3 = new ArrayList<PrdBase>();
		this.prdBases4 = new ArrayList<PrdBase>();
		this.prdBases5 = new ArrayList<PrdBase>();
		this.prdBases6 = new ArrayList<PrdBase>();
		this.prdBases7 = new ArrayList<PrdBase>();
		this.prdPackages = new ArrayList<PrdPackage>();
	}

	public List<PrdBase> getPrdBases1() {
		return prdBases1;
	}

	public void setPrdBases1(List<PrdBase> prdBases1) {
		this.prdBases1 = prdBases1;
	}

	public List<PrdBase> getPrdBases2() {
		return prdBases2;
	}

	public void setPrdBases2(List<PrdBase> prdBases2) {
		this.prdBases2 = prdBases2;
	}

	public List<PrdBase> getPrdBases3() {
		return prdBases3;
	}

	public void setPrdBases3(List<PrdBase> prdBases3) {
		this.prdBases3 = prdBases3;
	}

	public List<PrdBase> getPrdBases4() {
		return prdBases4;
	}

	public void setPrdBases4(List<PrdBase> prdBases4) {
		this.prdBases4 = prdBases4;
	}

	public List<PrdBase> getPrdBases5() {
		return prdBases5;
	}

	public void setPrdBases5(List<PrdBase> prdBases5) {
		this.prdBases5 = prdBases5;
	}

	public List<PrdBase> getPrdBases6() {
		return prdBases6;
	}

	public void setPrdBases6(List<PrdBase> prdBases6) {
		this.prdBases6 = prdBases6;
	}

	public List<PrdBase> getPrdBases7() {
		return prdBases7;
	}

	public void setPrdBases7(List<PrdBase> prdBases7) {
		this.prdBases7 = prdBases7;
	}

	public List<PrdPackage> getPrdPackages() {
		return prdPackages;
	}

	public void setPrdPackages(List<PrdPackage> prdPackages) {
		this.prdPackages = prdPackages;
	}
	
	
}