

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FloorManager {
	private ArrayList<Floor> mazeList = new ArrayList<Floor>();
	//x: 120, #: 35, . : 46, ~ : 126
	int [][] floor1 = 
		{ {'x','x','#','#','I','#','#','x','x' },
		  {'x','#','.','.','.','.','.','#','x' },
		  {'#','.','.','.','.','.','.','.','#' },
		  {'#','.','.','.','.','.','.','.','#'},
		  {'x','#','.','.','.','.','.','#','x'},
		  {'x','x','#','.','.','.','#','x','x'},
		  {'x','x','x','#','~','#','x','x','x'},
		  {'x','x','x','#','.','#','x','x','x'},
		  {'x','x','x','#','.','#','x','x','x'},
		  {'x','x','x','#','.','#','x','x','x'},
		  {'x','x','x','#','O','#','x','x','x'}
		};
	
	int [][] floor2 = 
		{ {'x','x','x','x','#','#','#','#','#','I','#','x','x','x','x' },
		  {'x','x','x','#','.','.','.','.','.','.','.','#','x','x','x' },
		  {'x','x','#','.','.','.','.','.','.','.','.','.','#','x','x' },
		  {'x','#','.','.','.','.','.','.','.','.','.','.','.','#','x' },
		  {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','#' },
		  {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','#' },
		  {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','#' },
		  {'#','#','.','.','.','.','.','.','.','.','.','.','.','#','#' },
		  {'#','#','#','.','.','.','.','.','.','.','.','.','#','#','#' },
		  {'#','#','#','#','.','.','.','.','.','.','.','#','#','#','#' },
		  {'#','#','#','#','#','.','.','.','.','.','#','#','#','#','#' },
		  {'#','#','#','#','#','#','.','.','.','#','#','#','#','#','#' },
		  {'#','#','#','#','#','#','#','.','#','#','#','#','#','#','#' },
		  {'#','#','#','#','#','#','#','.','#','#','#','#','#','#','#' },
		  {'#','#','#','#','#','#','#','O','#','#','#','#','#','#','#' }
		};
	
	int [][] floor3 = 
		{ 
			{'x','x','#','I','#','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
			{'x','x','#','.','#','x','x','x','x','x','x','#','#','#','#','#','#','x','x','x','x'},
			{'x','x','#','.','#','x','x','x','x','x','x','#','.','.','.','.','#','x','x','x','x'},
			{'x','x','#','.','#','x','x','x','x','x','x','#','.','.','.','.','#','x','x','x','x'},
			{'x','x','#','~','#','#','#','#','#','#','#','#','#','~','#','#','#','x','x','x','x'},
			{'x','x','#','.','.','.','.','.','.','~','.','.','#','.','.','#','x','x','x','x','x'},
			{'x','x','#','#','#','#','~','#','.','#','.','#','#','#','~','#','x','x','x','x','x'},
			{'#','#','#','#','#','#','.','#','.','#','.','.','.','.','.','#','x','x','x','x','x'},
			{'#','.','.','.','.','#','.','#','.','#','.','#','#','#','#','#','#','#','#','#','x'},
			{'#','.','.','.','.','~','.','#','.','.','.','.','.','~','.','.','.','.','.','#','x'},
			{'#','~','#','#','#','#','#','#','#','#','#','#','#','#','.','.','.','.','.','#','x'},
			{'#','.','~','.','.','.','.','.','#','#','#','#','#','#','.','.','.','.','.','#','x'},
			{'#','.','#','.','.','.','.','.','#','.','.','.','.','~','.','.','.','.','.','#','x'},
			{'#','~','#','#','#','#','#','#','#','~','#','#','#','#','#','#','#','#','#','#','x'},
			{'#','.','.','.','.','.','.','.','~','.','#','x','x','x','x','x','x','x','x','x','x'},
			{'#','.','.','.','.','.','.','.','#','~','#','#','#','#','x','x','x','x','x','x','x'},
			{'#','#','#','#','#','#','#','#','#','.','.','.','.','#','x','x','x','x','x','x','x'},
			{'#','.','.','.','.','~','.','.','~','.','.','.','.','#','x','x','x','x','x','x','x'},
			{'#','.','.','.','.','#','#','#','#','.','.','.','.','#','x','x','x','x','x','x','x'},
			{'#','.','.','.','.','#','x','x','#','#','#','#','#','#','x','x','x','x','x','x','x'},
			{'#','#','#','#','O','#','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'}
		};
	
	
	
	
	public FloorManager(){
		mazeList.add(new Floor(1,floor1));
		mazeList.add(new Floor(2,floor2));
		mazeList.add(new Floor(3,floor3));
		ArrayList<Floor> newMazeList = new ArrayList<Floor>();
		//Add buffer to edge
		
		for(int i = 0 ; i< mazeList.size(); i++){
			int[][] floor = mazeList.get(i).getFloor();
			int [][] newFloor = new  int[floor.length+2][floor[0].length+2];
			for(int m = 1; m < newFloor.length-1; m++)
			{
				for(int n = 1; n < newFloor[m].length-1; n++)
				{
					newFloor[m][n] = floor[m-1][n-1]; //add buffer edge
				}
			}
			//mazeList.remove(i);
			newMazeList.add(new Floor(mazeList.get(i).getFloorID(),newFloor));
			
		} 
		mazeList = newMazeList;
	}
	
	public static int[][] readFloor(String floorFileName) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("B1.txt"));
		String floorName = null;
		int countLine = 0;
		int num1 = 0, num2 = 0;
		int[][] floor = null; 
		int row = 1;
		while(sc.hasNext())
		{
			if(countLine == 0)
			{
				floorName = sc.nextLine();
			}else if(countLine == 1)
			{
				num1 = Integer.parseInt(sc.next());
				num2 = Integer.parseInt(sc.next());
				sc.nextLine();
				floor = new  int[num1+2][num2+2];
			}else
			{
				String s = sc.nextLine();
				
				for(int i = 1; i < floor[row].length-1; i++)
				{
					floor[row][i] = s.charAt(i-1);
				}
				row++;
			}
			
			countLine++;
		}
		
		return floor;
	}
	
	public static String toString(int[][] floor){
		String s="";
		for(int i = 1; i< floor.length-1; i++){
			for(int j = 1; j < floor[i].length-1; j++){
				
				s += ((char)floor[i][j] +" ");
			}
		
			s += "\n";
		}
		return s;
	}
	
	
	public ArrayList<Floor> getFloorList(){
		ArrayList<Floor> newMazeList = new ArrayList<Floor>();
		//Add buffer to edge
		
		for(int i = 0 ; i< mazeList.size(); i++){
			int[][] floor = mazeList.get(i).getFloor();
			int [][] newFloor = new  int[floor.length+2][floor[0].length+2];
			for(int m = 1; m < newFloor.length-1; m++)
			{
				for(int n = 1; n < newFloor[m].length-1; n++)
				{
					newFloor[m][n] = floor[m-1][n-1]; //add buffer edge
				}
			}
			//mazeList.remove(i);
			newMazeList.add(new Floor(mazeList.get(i).getFloorID(),newFloor));
			
		} 
		return newMazeList;
	}
	
	public Floor getFloorByID(int floorID){
		//Floor foundFloor = null;
		for(int i = 0; i< mazeList.size(); i++){
			if(mazeList.get(i).getFloorID() == floorID){
				return mazeList.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Floor> getMazeList(){
		return mazeList;
	}

}
