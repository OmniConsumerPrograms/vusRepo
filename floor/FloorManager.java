package floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FloorManager {
	/*
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(new File("B1.txt"));
		String floorName = null;
		int countLine = 0;
		int num1 = 0, num2 = 0;
		int[][] floor = null; 
		int row = 0;
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
				floor = new  int[num1][num2];
			}else{
				//System.out.println(floor.length);
				//System.out.println(sc.nextLine());
				String s = sc.nextLine();
				//System.out.println(s);
				for(int i = 0; i < floor[row].length; i++){
					floor[row][i] = s.charAt(i);
				}
				row++;
			}
			
			countLine++;
		}
		//System.out.println(num1);
		for(int i = 0; i< floor.length; i++){
			for(int j = 0; j < floor[i].length; j++){
				//floor[i][j] = s.charAt(j);
				System.out.print((char)floor[i][j] +" ");
			}
			System.out.println();
		}
		
	} */
	public static int[][] readFloor(String floorFileName) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("B1.txt"));
		String floorName = null;
		int countLine = 0;
		int num1 = 0, num2 = 0;
		int[][] floor = null; 
		int row = 0;
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
				floor = new  int[num1][num2];
			}else
			{
				String s = sc.nextLine();
				
				for(int i = 0; i < floor[row].length; i++)
				{
					floor[row][i] = s.charAt(i);
				}
				row++;
			}
			
			countLine++;
		}
		
		return floor;
	}
	
	public String toString(int[][] floor){
		String s="";
		for(int i = 0; i< floor.length; i++){
			for(int j = 0; j < floor[i].length; j++){
				//floor[i][j] = s.charAt(j);
				//System.out.print((char)floor[i][j] +" ");
				s += ((char)floor[i][j] +" ");
			}
			//System.out.println();
			s += "\n";
		}
		return s;
	}

}
