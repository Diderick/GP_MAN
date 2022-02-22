package net.leroisoftware.gpman;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;


public class MakeDB {
	
	static String path = System.getProperty("user.dir");
    static String url = "jdbc:sqlite:" + path + "/src/net/leroisoftware/gpman/data/gpmandatabase.db";
	
	public static void dropDatabase() {
		
		String sql = "DROP TABLE player";
		
		 try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }		
	}
	
	public static void deleteById(int id) {
		String sql = "DELETE FROM player where Player_id = " + id;
		
		 try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }	
		
		
	}
	
	public static ArrayList<String> populateTable() throws SQLException, IOException, ClassNotFoundException {
		
		ArrayList<String> aList = new ArrayList<String>();
		
		String sql = "SELECT BSA_Number, name, surname, id_number FROM PLAYER";
		
		try (Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement()){
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				aList.add(rs.getString("BSA_Number"));
				aList.add(rs.getString("Name"));
				aList.add(rs.getString("surname"));
				aList.add(rs.getString("id_number"));
				
			}
			rs.close();
			stmt.close();
		};
		
		System.out.print(aList);
		return aList;
		
	}
	
	
	public static ArrayList<String> getPlayerData() throws SQLException, IOException, ClassNotFoundException {
		
		ArrayList<String> aList = new ArrayList<String>();
		
		String sql = "SELECT Player_Id, BSA_Number, name, surname, id_number, gender FROM PLAYER";
		
		try (Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement()){
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				aList.add(rs.getString("Player_Id"));
				aList.add(rs.getString("BSA_Number"));
				aList.add(rs.getString("Name"));
				aList.add(rs.getString("surname"));
				aList.add(rs.getString("id_number"));
				aList.add(rs.getString("gender"));

				
			}
			rs.close();
			stmt.close();
		};
		
		//System.out.print(aList);
		return aList;
		
	}
	
	public static int getHighestValue() {
				
		String sql = "SELECT MAX(BSA_Number) FROM player";
		
		 try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		return 0;	
		
		
		
	}
	
	
	public static void insertData(String bsaNumber,String playerName, String playerSurname, String playerIdNumber, String playerAge, 
									String playerProvince, String playerClub, String playerRole, String playerRace, String playerGender) 
									throws SQLException, IOException, ClassNotFoundException {
				
		String sql = "INSERT INTO player (BSA_Number, Name, Surname, ID_Number, Gender, Race, Province, Club, Player_Role) VALUES ('"
				+bsaNumber +"',"
				+"'" + playerName  +"',"
				+"'" + playerSurname +"',"
				+"'" + playerIdNumber  +"',"
				+"'" + playerGender  +"',"
				+"'" + playerRace +"',"
				+"'" + playerProvince +"',"
				+"'" + playerClub +"',"
				+"'" + playerRole
				
				+"')";
		
		if(bsaNumber.isEmpty() || playerName.isEmpty() || playerSurname.isEmpty() || playerGender.indexOf(0) == 0) {
			System.out.print("BSA Cannnot be empty");
		}
		else {
			try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException f) {
	            System.out.println(f.getMessage());
	        }		
			
		}
		
		 
	}

	
	
	public static void main(String[] args) throws Exception {
		
		String path = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + path + "/src/net/leroisoftware/gpman/data/gpmandatabase.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS player (\n"
                + "	Player_Id integer PRIMARY KEY,\n"
                + "	BSA_Number integer,\n"
                + " Name text, \n"
                + " Surname text, \n"
                + " ID_Number, \n"
                + " Gender, \n"
                + " Race text, \n"
                + " Province text, \n"
                + " Club text, \n"
                + " Cell_Number text, \n"
                + " Email_Address text, \n"
                + " Player_Role text \n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
		
	}
	
}
