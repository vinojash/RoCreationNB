package com.sedin.RoCreation;

import java.util.Iterator;
import java.util.List;
import com.documentum.bpm.IDfWorkflowEx;
import com.documentum.fc.client.IDfProcess;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfWorkflowBuilder;
import com.documentum.fc.common.DfException;

public class DeptFuncAct {

	private String currentDepartmentName = null;
	private String currentFunctionName = null;
	private String currentDepartmentOjectId = null;
	private String currentFunctionObjectId = null;
	private IDfSession mySession = null;
	private String roShortcode = null;

	public void process(List<ExcelObject> listExcelObject) {
		Iterator<ExcelObject> excelObjIterator = listExcelObject.iterator();
		while (excelObjIterator.hasNext()) {
			ExcelObject excelObject = excelObjIterator.next();
			excelObject.print();

			if (this.currentDepartmentName != excelObject.getDepartmentName()) {
				this.currentDepartmentName = excelObject.getDepartmentName();
				this.currentFunctionName = excelObject.getFunctionName();
				this.createDepartment(excelObject);
				this.createFunction(excelObject);
				this.createActivity(excelObject);
			} else if (this.currentFunctionName != excelObject.getFunctionName()) {
				this.currentFunctionName = excelObject.getFunctionName();
				this.createFunction(excelObject);
				this.createActivity(excelObject);
			} else {
				this.createActivity(excelObject);
			}

		}
	}

	private void createDepartment(ExcelObject excelObject) {
		System.out.println("Department Created..!");
		/* department creation code not required here */
		try {
			IDfSysObject department = (IDfSysObject) mySession.getObjectByQualification(
					"edmapp_org_folder where short_code='" + excelObject.getDepartmentShortCode()
							+ "' AND status='RO' AND ro_code='" + this.roShortcode + "' AND is_department=1;");
			currentDepartmentOjectId = department.getObjectId().getId();
		} catch (DfException e) {
			e.printStackTrace();
		}
	}

	private void createFunction(ExcelObject excelObject) {
		try {

			IDfProcess process = (IDfProcess) mySession.getObjectByQualification(
					"dm_process where object_name = 'Creation of Functions for Department RO' AND r_definition_state = 2 ORDER BY r_modify_date desc;");

			IDfWorkflowBuilder builder = mySession.newWorkflowBuilder(process.getObjectId());

			builder.initWorkflow();

			IDfWorkflowEx workflow = (IDfWorkflowEx) builder.getWorkflow();

			workflow.setPrimitiveObjectValue("category", "Function");
			workflow.setPrimitiveObjectValue("department_code", excelObject.getDepartmentShortCode());
			workflow.setPrimitiveObjectValue("department_folder_id", this.currentDepartmentOjectId);
			workflow.setPrimitiveObjectValue("function_code", excelObject.getFunctionSortCode());
			workflow.setPrimitiveObjectValue("function_name", excelObject.getFunctionName());
			workflow.setPrimitiveObjectValue("ho_ro", "RO");
			workflow.setPrimitiveObjectValue("ro_code", this.roShortcode);

			builder.runWorkflow();

			IDfSysObject function = (IDfSysObject) mySession.getObjectByQualification(
					"edmapp_org_folder where department='" + excelObject.getDepartmentShortCode() + "' AND short_code='"
							+ excelObject.getFunctionSortCode() + "' AND status='RO' AND ro_code='" + this.roShortcode
							+ "' AND is_function=1;");
			currentFunctionObjectId = function.getObjectId().getId();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private void createActivity(ExcelObject excelObject) {
		try {
			IDfProcess process = (IDfProcess) mySession.getObjectByQualification(
					"dm_process where object_name = 'Creation Of Activity Folder for Department RO' AND r_definition_state = 2 ORDER BY r_modify_date desc;");

			IDfWorkflowBuilder builder = mySession.newWorkflowBuilder(process.getObjectId());

			builder.initWorkflow();

			IDfWorkflowEx workflow = (IDfWorkflowEx) builder.getWorkflow();

			workflow.setPrimitiveObjectValue("activity_code", excelObject.getActivityShortCode());
			workflow.setPrimitiveObjectValue("activity_name", excelObject.getActivityName());
			workflow.setPrimitiveObjectValue("category", "Activity");
			workflow.setPrimitiveObjectValue("department_code", excelObject.getDepartmentShortCode());
			workflow.setPrimitiveObjectValue("function_code", excelObject.getFunctionSortCode());
			workflow.setPrimitiveObjectValue("function_folder_id", this.currentFunctionObjectId);
			workflow.setPrimitiveObjectValue("ho_ro", "RO");
			workflow.setPrimitiveObjectValue("ro_code", this.roShortcode);

			builder.runWorkflow();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setSession(IDfSession mySession) {
		this.mySession = mySession;
	}

	public IDfSession getSession() {
		return this.mySession;
	}

	public void setRoShortCode(String roShortCode) {
		this.roShortcode = roShortCode;

	}
}
