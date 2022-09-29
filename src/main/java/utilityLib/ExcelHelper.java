package utilityLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	public static String getCellData(String fileName, String sheetName, String colName, int rowNum) throws IOException {

		File f1 = new File(System.getProperty("user.dir") + "\\test-input\\" + fileName);
		FileInputStream fis = null;
		fis = new FileInputStream(f1);
		Workbook workbook = null;
		workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		int col_Num = 0;
		Row row = sheet.getRow(0);
		boolean flag = false;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				col_Num = i;
				flag = true;
				break;
			}
		}

		if (flag == false) {
			col_Num = -1;
		}

		String rcell = null;
		row = sheet.getRow(rowNum - 1);

		Cell cell = row.getCell(col_Num, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		if (cell.getCellType() == CellType.STRING) {
			rcell = cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
			rcell = NumberToTextConverter.toText(cell.getNumericCellValue());
			if (DateUtil.isCellDateFormatted(cell)) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date date = cell.getDateCellValue();
				rcell = df.format(date);
			}

		} else if (cell.getCellType() == CellType.BOOLEAN) {
			rcell = String.valueOf(cell.getBooleanCellValue());
		}

		else {
			rcell = "";
		}
		workbook.close();
		return rcell;
	}
	
// Get data object for data driven execution
	
	public static Object[][] getDataObject(String fileName, String sheetName) throws IOException{
		Row row;
		Cell cell;
		Object[][] data = null;
		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "\\test-input\\" + fileName);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		
		//get no of rows from sheet
		int rows = sheet.getPhysicalNumberOfRows();
		int clmns = sheet.getRow(0).getPhysicalNumberOfCells();
		data = new String[rows -1][clmns];
		for(int r =1 ; r < rows; r++) {
			for(int c=0; c< clmns; c++) {
				row = sheet.getRow(r);
				cell= row.getCell(c);
				
				if (cell !=null || cell.getCellType() != CellType.BLANK) {
				
					switch(cell.getCellType()) {
					
					case NUMERIC:
						data[r-1][c]=""+cell.getNumericCellValue();
						break;
						
					case STRING:
						data[r-1][c]=""+cell.getStringCellValue();
						break;
						
						default:
					}
					
				}
				
				else {
					data[r-1][c]="";
				}
				
			}
		}
		workbook.close();
		return data;
		
		
	}
	
}
