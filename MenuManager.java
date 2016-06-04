

import java.sql.SQLException;
import java.util.Scanner;

public class MenuManager {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static String printFirstMenu() throws Exception
	{
		System.out.println("New Game(N)");
		SQLManager.openDatabase();
		SQLManager.createTable("SAVE");
		SQLManager.selectTable();
		if(SQLManager.rs.next()){
			System.out.println("Resume Game(R)");
		}
		
		
		System.out.println("Quit Game(Q)");
		System.out.print("Enter your choice: ");
		return sc.nextLine();
	}

}
