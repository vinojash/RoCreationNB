package com.sedin.RoCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public List<ExcelObject> readMetaDataFromExcel(String excelFilePath) {
		File excelFile = null;
		if (null != excelFilePath && !excelFilePath.isBlank()) {
			excelFile = new File("./excelFiles/RoInfo.xlsx");
		} else {
			excelFile = new File(excelFilePath);
		}

		List<ExcelObject> listExcelObject = null;

		if (excelFile.exists()) {

			Workbook workbook = null;

			try {
				FileInputStream excelFileInputStream = new FileInputStream(excelFile);
				workbook = new XSSFWorkbook(excelFileInputStream);
				Iterator<Sheet> sheetIterator = workbook.sheetIterator();
				if (sheetIterator.hasNext()) {
					listExcelObject = getSheetInformtion(sheetIterator.next());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(workbook);
			}
		}

		return listExcelObject;
	}

	private List<ExcelObject> getSheetInformtion(Sheet sheet) {

		List<ExcelObject> listExcelObject = new ArrayList<ExcelObject>();

		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {

			Row currentRow = rowIterator.next();

			if (currentRow.getRowNum() != 0) {

				ExcelObject excelObject = new ExcelObject();
				excelObject.setRowNumber(currentRow.getRowNum() + 1);
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();

					switch (currentCell.getColumnIndex()) {
					case 0: {
						excelObject.setDepartmentName(currentCell.getStringCellValue().trim());
					}
					case 1: {
						excelObject.setDepartmentShortCode(currentCell.getStringCellValue().trim());
					}
					case 2: {
						excelObject.setFunctionName(currentCell.getStringCellValue().trim());
					}
					case 3: {
						excelObject.setFunctionSortCode(currentCell.getStringCellValue().trim());
					}
					case 4: {
						excelObject.setActivityName(currentCell.getStringCellValue().trim());
					}
					case 5: {
						excelObject.setActivityShortCode(currentCell.getStringCellValue().trim());
					}
					default:

					}
				}
				listExcelObject.add(excelObject);
			}

		}
		return listExcelObject;
	}
}
