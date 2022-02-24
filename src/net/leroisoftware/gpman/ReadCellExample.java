package net.leroisoftware.gpman;

//reading value of a particular cell  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadCellExample {
	public static void main(String[] args) {
		ReadCellExample rc = new ReadCellExample(); // object of the class
//reading the value of 2nd row and 2nd column  		
		
		for(int i = 0 ; i < 14641 ; i ++) {
			String vOutput = rc.ReadCellData(i, 0);
			String vOutput1 = rc.ReadCellData(i, 1);
			String vOutput2 = rc.ReadCellData(i, 2);
			String vOutput3 = rc.ReadCellData(i, 3);
			String vOutput4 = rc.ReadCellData(i, 4);
			String vOutput5 = rc.ReadCellData(i, 5);
			//String vOutput6 = rc.ReadCellData(i, 6);


			System.out.println(vOutput + " " + vOutput1+ " " + vOutput2+ " " + vOutput3+ " " + vOutput4+ " " + vOutput5);// " " + vOutput6);
			
		}
		
	
	}

//method defined for reading a cell  
	public String ReadCellData(int vRow, int vColumn) {
		String value = null; // variable for storing the cell value
		Workbook wb = null; // initialize Workbook null
		try {
//reading data from a file in the form of bytes  
			FileInputStream fis = new FileInputStream("C:\\\\Users\\\\djdaf\\\\OneDrive\\\\Desktop\\\\Players.xlsx");
//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(0); // getting the XSSFSheet object at given index
		Row row = sheet.getRow(vRow); // returns the logical row
		Cell cell = row.getCell(vColumn); // getting the cell representing the given column
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: // field that represents string cell type
			value = cell.getStringCellValue(); // getting cell value
			break;
		case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
			double a = cell.getNumericCellValue();
			value = String.valueOf(a);// getting cell value
			break;
		default:
		
		return value; // returns the cell value
	}
}