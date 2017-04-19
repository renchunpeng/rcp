package com.lflweb.entity.prd;

import java.util.ArrayList;
import java.util.List;

public class PrdSelectMix{
	private List<PrdBase> prdBases;
	
	private List<PrdPackage> prdPackages;
	
	public PrdSelectMix() {
		this.OnInit();
	}
	
	public void OnInit() {
		this.prdBases = new ArrayList<PrdBase>();
		this.prdPackages = new ArrayList<PrdPackage>();
	}

	public List<PrdBase> getPrdBases() {
		return prdBases;
	}

	public void setPrdBases(List<PrdBase> prdBases) {
		this.prdBases = prdBases;
	}

	public List<PrdPackage> getPrdPackages() {
		return prdPackages;
	}

	public void setPrdPackages(List<PrdPackage> prdPackages) {
		this.prdPackages = prdPackages;
	}
	
	
}