package net.leroisoftware.gpman;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sqlitetutorial.net
 */
public class CreateNewTable {

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
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


