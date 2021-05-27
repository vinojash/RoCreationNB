package com.testCases;

import java.util.List;

import com.sedin.RoCreation.DeptFuncAct;
import com.sedin.RoCreation.Excel;
import com.sedin.RoCreation.ExcelObject;
import com.sedin.RoCreation.SessionDFC;

public class Execute {

	public static void main(String[] args) {

		// repository name
		System.out.println(args[0]);
		// user name
		System.out.println(args[1]);
		// password
		System.out.println(args[2]);
		// file path
		System.out.println(args[3]);
		// RO short code
		System.out.print(args[4]);

		Excel excel = new Excel();
		List<ExcelObject> listExcelObject = null;
		listExcelObject = excel.readMetaDataFromExcel(args[3]);

		DeptFuncAct deptFuncAct = new DeptFuncAct();
		SessionDFC sessionDFC = new SessionDFC();

		deptFuncAct.setSession(sessionDFC.getSession(args[0], args[1], args[2]));
		deptFuncAct.setRoShortCode(args[4]);
		deptFuncAct.process(listExcelObject);

		sessionDFC.releaseSession(deptFuncAct.getSession());

	}

}
