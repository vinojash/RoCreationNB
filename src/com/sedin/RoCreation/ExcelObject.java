package com.sedin.RoCreation;

public class ExcelObject {

	private String departmentName = null;
	private String departmentShortCode = null;

	private String functionName = null;
	private String functionSortCode = null;

	private String activityName = null;
	private String activityShortCode = null;

	private int rowNumber = 0;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentShortCode() {
		return departmentShortCode;
	}

	public void setDepartmentShortCode(String departmentShortCode) {
		this.departmentShortCode = departmentShortCode;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionSortCode() {
		return functionSortCode;
	}

	public void setFunctionSortCode(String functionSortCode) {
		this.functionSortCode = functionSortCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityShortCode() {
		return activityShortCode;
	}

	public void setActivityShortCode(String activityShortCode) {
		this.activityShortCode = activityShortCode;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public void print() {
		System.out.println(
				"\nRow No : "+
				this.rowNumber+
				"\t Dept Name : "+
				this.departmentName+
				"\t Dept ShortCode : "+
				this.departmentShortCode+
				"\t Func Name : "+
				this.functionName+
				"\t Func ShotCode : "+
				this.functionSortCode+
				"\t Act Name : "+
				this.activityName+
				"\t Act ShortCode : "+
				this.activityShortCode				
				);
	}

}
