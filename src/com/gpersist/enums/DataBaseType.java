package com.gpersist.enums;

public enum DataBaseType {
	Oracle10("Oracle10"), SqlServer2005("SqlServer2005");
	
	private final String database;
	
	private DataBaseType(String database) {
		this.database = database;
	}
	
	public String getDatabase() {
		return this.database;		
	}
	
	public static DataBaseType parse(String database) {
		if (database != null) {
			for (DataBaseType type : DataBaseType.values()) {
				if (database.equalsIgnoreCase(type.database))
					return type;
			}
		}
		
		return DataBaseType.SqlServer2005;
	}
}
