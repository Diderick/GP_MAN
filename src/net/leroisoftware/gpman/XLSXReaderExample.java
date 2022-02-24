package net.leroisoftware.gpman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReaderExample {

	static String BSA_Number;
	double seqNum;
	static String playerName;
	static String playerSurname;
	static String playerIdNumber;
	static String playerGender;
	static String playerAge;
	static String playerClub;
	static String playerActive;
	static String playerCredits;
	static String termDates;

	static ArrayList<String> aList = new ArrayList<String>();
	static ArrayList<String> compareList = new ArrayList<String>();
	static ArrayList<String> dataList = new ArrayList<String>();
	
	static int rowCount = 0;

	
	
	public static void setBSA_Number(String BSA_Num) {
		BSA_Number = BSA_Num;
		
	}
	
	public void setSeqNum(double seqNum) {
		this.seqNum = seqNum;
	}

	public static String getPlayerName() {
		return playerName;
	}

	public static void setPlayerName(String playerName) {
		XLSXReaderExample.playerName = playerName;
	}

	public static String getPlayerSurname() {
		return playerSurname;
	}

	public static void setPlayerSurname(String playerSurname) {
		XLSXReaderExample.playerSurname = playerSurname;
	}

	public static String getPlayerIdNumber() {
		return playerIdNumber;
	}

	public static void setPlayerIdNumber(String playerIdNumber) {
		XLSXReaderExample.playerIdNumber = playerIdNumber;
	}

	public static String getPlayerGender() {
		return playerGender;
	}

	public void setPlayerGender(String playerGender) {
		this.playerGender = playerGender;
	}

	public static String getPlayerAge() {
		return playerAge;
	}

	public void setPlayerAge(double playerAge) {
		XLSXReaderExample.playerAge = playerAge;
	}

	public static String getPlayerClub() {
		return playerClub;
	}

	public void setPlayerClub(String playerClub) {
		this.playerClub = playerClub;
	}

	public String getPlayerActive() {
		return playerActive;
	}

	public void setPlayerActive(String playerActive) {
		this.playerActive = playerActive;
	}

	public String getPlayerCredits() {
		return playerCredits;
	}

	public void setPlayerCredits(String playerCredits) {
		this.playerCredits = playerCredits;
	}

	public String getTermDates() {
		return termDates;
	}

	public void setTermDates(String termDates) {
		this.termDates = termDates;
	}

	public static ArrayList<String> getaList() {
		return aList;
	}

	public static void setaList(ArrayList<String> aList) {
		XLSXReaderExample.aList = aList;
	}

	public static String getBSA_Number() {
		return BSA_Number;
	}

	{
		
	}

	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\djdaf\\OneDrive\\Desktop\\Players.xlsx"); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						aList.add(cell.getStringCellValue());
						compareList.add(cell.getStringCellValue());
						rowCount+=1;
						break;
					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
						aList.add(String.valueOf(cell.getNumericCellValue()));
						compareList.add(String.valueOf(cell.getNumericCellValue()));
						rowCount+=1;
						break;
					default:
					}
					System.out.println();
				}
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Size of list is: " + aList.size());
		
		for(int j = 0; j <= 7; j++) {
			aList.remove(0);
			compareList.remove(0);
		}
		for(int j = 0; j <= 7; j++) {
			compareList.remove(0);
		}
		
		
		for(int k = 0; k <= 7; k++) {
			dataList.add(aList.get(k));
		}
		

	
		//System.out.println(" THIS IS datalist " + dataList);
		//System.out.println(" THIS IS compareList " + compareList.get(0));
		int rowCountDivided = rowCount/8;
		while(rowCountDivided != 0) {
			System.out.println(rowCountDivided);
			setBSA_Number(aList.get(0));
			setPlayerName(aList.get(1));
			setPlayerSurname(aList.get(2));
			setPlayerIdNumber(aList.get(3));
			

			
			try {
				MakeDB.insertData(getBSA_Number(), getPlayerName(), getPlayerSurname(), getPlayerIdNumber(), getPlayerAge(),
									getPlayerProvince(), getPlayerClub(), getPlayerRole(), getPlayerRace(), getPlayerGender());
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			for(int j = 0; j <= 7; j++) {
				if(!aList.isEmpty()) {
					aList.remove(0);
				}
			}
			
			
			
			rowCountDivided--;
		}
		
		System.out.println(" THIS IS aList " + aList.get(0));
		System.out.println(" THIS IS aList " + aList.get(1));
		System.out.println(" THIS IS aList " + aList.get(2));
		System.out.println(" THIS IS aList " + aList.get(3));
		System.out.println(" THIS IS aList " + aList.get(4));
		System.out.println(" THIS IS aList " + aList.get(5));
		System.out.println(" THIS IS aList " + aList.get(6));
		System.out.println(" THIS IS aList " + aList.get(7));
		
		
		

		




		

	//	while (!aList.isEmpty()) {
	//		aList.remove(0);
		//}
	/*	for (int i = 8; i < aList.size(); i++) {
			if(compareList.get(i) == aList.get(i)) {
				System.out.println("Size of list is: " + aList.get(i));

			}
		} */

	}

	private static String getPlayerRace() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getPlayerRole() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getPlayerProvince() {
		// TODO Auto-generated method stub
		return null;
	}
}