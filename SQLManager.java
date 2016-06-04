

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLManager {

	
	public static Connection c = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
    
    public static void openDatabase() throws Exception
    {
  	  Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:game.db");
        //System.out.println("Opened database successfully");
        
    }
    public static void createTable(String tableName) throws SQLException{
    	 stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS SAVE " +
                      "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                      " MAZELIST           CHAR(100)    NOT NULL, " + 
                      " CURRENTFLOOR           CHAR(100)    NOT NULL, " + 
                      " POSITION         CHAR(100)    NOT NULL)"; 
         stmt.executeUpdate(sql);
         
    }
    
    public static void insertTable(String mazeList, String currentFloor,String position) throws SQLException
	{
		stmt = c.createStatement();
		String sql = "DELETE from SAVE;";
	    stmt.executeUpdate(sql);
		sql = "INSERT INTO SAVE (MAZELIST,CURRENTFLOOR,POSITION) " +
                   "VALUES ('"+mazeList + "', '"+currentFloor+"', '" + position+"');"; 
		stmt.executeUpdate(sql);
		stmt.close();
	      c.close();
	}
    
    public static void selectTable() throws SQLException
	{
		stmt = c.createStatement();
		rs = stmt.executeQuery( "SELECT * FROM SAVE ORDER BY ID DESC LIMIT 1;" );
		//return rs;
		//stmt.close();
	      //c.close();
	}

}
