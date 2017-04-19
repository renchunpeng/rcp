package com.gpersist.enums;

public enum DataAction {
	Create(2), Modify(3), Delete(4), Deal(7), Valid(8), InValid(9), Check(10), UnCheck(11), Upload(12),
	Special01(13), Special02(14), Special03(15), Submit(16);
	
	private final int action;
	
	private DataAction(int action) {
		this.action = action;
	}
	
	public int getAction () {
		return this.action;		
	}
}
