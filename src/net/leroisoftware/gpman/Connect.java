package net.leroisoftware.gpman;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;  
   
public class Connect {  
     /** 
     * Connect to a sample database 
     */  
    Connection connect() {  
    	
    	// db parameters  
        String path = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + path + "/src/net/leroisoftware/gpman/data/gpmandatabase.db";   
        Connection conn = null;  
        try {  
            
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
            System.out.println("Working Directory = " + path);

              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        }
		return conn;  
    } 
    
    public void insert(String name) {
        String sql = "INSERT INTO player(name) VALUES(?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    
    
    
    
    

}  