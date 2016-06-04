

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;



public class GameManager {
	private static int curFloorNo;
	private static Position prevPos;
	private static Position curPos;
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Floor> mazeList = new ArrayList<Floor>();
	private static FloorManager floorManager = new FloorManager();
	
	
	public static void startGame() throws Exception
	{
		
		String choice = MenuManager.printFirstMenu();
		if(choice.equalsIgnoreCase("N"))
		{
			startNewGame();
		}
		else if(choice.equalsIgnoreCase("R"))
		{
			resumeGame();
		}
		else if(choice.equalsIgnoreCase("Q"))
		{
			quitGame();
		}
		
	}
	
	private static void resumeGame() throws Exception {
		
		SQLManager.openDatabase();
		//SQLManager sqlManager = new SQLManager();
		
		 SQLManager.selectTable();
		
		//int id = rs.getInt("ID");
		String mazeListS = SQLManager.rs.getString("MAZELIST");
        String positionS = SQLManager.rs.getString("POSITION");
        String curentFloorS = SQLManager.rs.getString("CURRENTFLOOR");
        SQLManager.rs.close();
        //SQLManager.c.close();
        curFloorNo = Integer.parseInt(curentFloorS);
        String[] positionArr = positionS.split(" ");
        curPos = new Position(Integer.parseInt(positionArr[0]),Integer.parseInt(positionArr[1]));
        System.out.println(positionS);
        String[] id = mazeListS.split(" ");
		for(int i = 0; i< id.length; i++){
        	mazeList.add(floorManager.getFloorByID(Integer.parseInt(id[i])));
        }
		
		//System.out.println(id);
		for(int k = (curFloorNo-1) ; k< mazeList.size(); k++)
		{
			curFloorNo = k +1;
			int[][] floor = mazeList.get(k).getFloor();
			boolean canMove = true;
			
			System.out.println("You are at floor: "+ curFloorNo);
			while(canMove)
			{
				runGame(curFloorNo); //if null, means start the game
				printMenu();
				System.out.print("Your choice:");
				int choice = Integer.parseInt(sc.nextLine());
				Position temp = curPos; 
				move(choice);
				if(floor[curPos.x][curPos.y] == 'O')
				{
					break;
					
				}
				else if (floor[curPos.x][curPos.y] == 0 || floor[curPos.x][curPos.y] == '#')
				{
					curPos = temp;
					System.out.println("Invalid Move. Move another direction");
				}
				else
				{
					int randomCounter = randomCounter();
					System.out.println("Random Counter: "+ randomCounter);
				}
				
				
			}
			prevPos = null;
			curPos = null;
		}
		SQLManager.stmt.close();
		SQLManager.c.close();
	}

	private static void quitGame()
	{
		System.exit(0);
	}
	
	private static void startNewGame() throws Exception
	{
		System.out.println("New Game Started:");
	
		buildMaze();
		
		
		
		
		for(int k = 0 ; k< mazeList.size(); k++)
		{
			curFloorNo = k +1;
			int[][] floor = mazeList.get(k).getFloor();
			boolean canMove = true;
			prevPos = null;
			curPos = null;
			System.out.println("You are at floor: "+ curFloorNo);
			while(canMove)
			{
				runGame(curFloorNo); //if null, means start the game
				printMenu();
				System.out.print("Your choice:");
				int choice = Integer.parseInt(sc.nextLine());
				Position temp = curPos; 
				move(choice);
				if(floor[curPos.x][curPos.y] == 'O')
				{
					break;
					
				}
				else if (floor[curPos.x][curPos.y] == 0 || floor[curPos.x][curPos.y] == '#')
				{
					curPos = temp;
					System.out.println("Invalid Move. Move another direction");
				}
				else
				{
					int randomCounter = randomCounter();
					System.out.println("Random Counter: "+ randomCounter);
				}
				
				
			}
		}
		
		/*
		for(int k = 0 ; k< mazeList.size(); k++){
			int[][] floor = mazeList.get(k).getFloor();
			//System.out.println(floorManager.toString(floor));
			Scanner sc = new Scanner(System.in);
			Position cur = null;
			for(int i = 1; i < floor[1].length-1; i++){
				if(floor[1][i] ==  'I')
				{
					cur = new Position(1,i);
					floor[1][i]= 'P';
					break;
				}
			}
			
			boolean canMove = true;
			while(canMove){
				System.out.println(floorManager.toString(floor));
				printMenu();
				System.out.print("Your move:");
				int move = Integer.parseInt(sc.nextLine());
				floor[cur.x][cur.y] = '.';
				Position temp = cur; 
				//System.out.println(cur.x+" "+ cur.y);
				cur = move(move, cur,mazeList,k+1,cur.x+" "+cur.y);
				if(floor[cur.x][cur.y] == 0 || floor[cur.x][cur.y] == '#' )
				{
					cur = temp;
					System.out.println("Invalid Move. Move another direction");
					
				}
				else if(floor[cur.x][cur.y] == 'O')
				{
					break;
					
				}
				else
				{
					int randomCounter = randomCounter();
					System.out.println("Random Counter: "+ randomCounter);
				}
				
				floor[cur.x][cur.y] = 'P';
				
			}
		} */
	}
	private static int randomCounter(){
		Random rand = new Random();
		return rand.nextInt(10);
	}
	
	private static void printMenu(){
		System.out.println("1. Move up");
		System.out.println("2. Move down");
		System.out.println("3. Move left");
		System.out.println("4. Move right");
		System.out.println("5. Save");
	}
	
	/*
	private static Position move(int move,Position cur,ArrayList<Floor> mazeList,int currentFloor,String position) throws Exception{
		int x = cur.x;
		int y = cur.y;
		switch(move){
			case 1:
				x -= 1;
				break;
			case 2: 
				x += 1;
				break;
			case 3: 
				y -= 1;
				break;
			case 4: 
				y += 1;
				break;
			case 5: 
				saveGame(mazeList,currentFloor,position);
				//return;
				break;
		}
		return new Position(x,y);
	}
	*/
	private static void move(int choice) throws Exception
	{
		int x = curPos.x;
		int y = curPos.y;
		prevPos = new Position(x,y);
		switch(choice){
			case 1:
				x -= 1;
				break;
			case 2: 
				x += 1;
				break;
			case 3: 
				y -= 1;
				break;
			case 4: 
				y += 1;
				break;
			case 5: 
				saveGame();
				return;
				//break;
		}
		curPos =  new Position(x,y);
	}
	/*
	private static void saveGame(ArrayList<Floor> mazeList,int currentFloor,String position) throws Exception{
		SQLManager.openDatabase();
		SQLManager.createTable("SAVE");
		String mazeListS = "";
		for(int i = 0; i< mazeList.size();i++){
			mazeListS += (mazeList.get(i).getFloorID()+" ");
		}
		SQLManager.insertTable(mazeListS, currentFloor+"",position);
		//createTable();
	}
	*/
	
	private static void saveGame() throws Exception
	{
		SQLManager.openDatabase();
		//SQLManager sqlManager = new SQLManager();
		SQLManager.createTable("SAVE");
		String mazeListS = "";
		for(int i = 0; i< mazeList.size();i++){
			mazeListS += (mazeList.get(i).getFloorID()+" ");
		}
		SQLManager.insertTable(mazeListS, curFloorNo+"",curPos.x + " "+curPos.y);
		//SQLManager.stmt.close();
		//SQLManager.c.close();
		//createTable();
	}
	
	private static void buildMaze()
	{
		
		mazeList = floorManager.getMazeList();
		//shuffle list
		Collections.shuffle(mazeList);
	}
	private static void runGame(int curFloorNo )
	{
		Floor curFloor = mazeList.get(curFloorNo-1); //curFloorNo used 1-basex index
		int[][] curFloorMatrix = curFloor.getFloor();
		if(curPos == null)
		{
			System.out.println("Null");
			for(int i = 1; i < curFloorMatrix.length-1; i++)
			{
				if(curFloorMatrix[1][i] ==  'I')
				{
					curPos = new Position(1,i);
					curFloorMatrix[1][i]= 'P';
					break;
				}
			}
		}
		else
		{
			//curFloorMatrix[1][i]= 'P';
		}
		
		if(prevPos != null)
		{
			curFloor.getFloor()[prevPos.x][prevPos.y] = '.';
		}
		
		curFloor.getFloor()[curPos.x][curPos.y] = 'P';
		System.out.println(FloorManager.toString(curFloor.getFloor()));
		
				
				
		
	}
	

}
