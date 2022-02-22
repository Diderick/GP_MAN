package net.leroisoftware.gpman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Data_Helper {
	
	public boolean checkValues() {
		
		return true;
		
	}
	
	
    public void insertPlayerData(String bsaNumber, String name, String surname, String idNumber,
    		String gender, String age, String race, String province, String club, String role, String cellNumber,
    		String emailAddress, String playerRole) {
        // SQLite connection string
    	String path = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + path + "/src/net/leroisoftware/gpman/data/gpmandatabase.db";   
        
        // SQL statement for creating a new table
       	String sql = "INSERT INTO Player "
       			+ "(Player_Id, BSA_Number, Name, Surname, ID_Number, Gender, Race, Province, Club, Cell_Number, Email_Address,  Player_Role)"
       			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"; 
       			
     
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, bsaNumber);
        	

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
