package floor;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FloorManager floorManager = new FloorManager();
		int[][] floor = FloorManager.readFloor("B1.txt");
		System.out.println(floorManager.toString(floor));
		Position cur = null;
		for(int i = 0; i< floor[0].length; i++){
			if(floor[0][i] ==  46)
			{
				cur = new Position(0,i);
				floor[0][i]= 'P';
				break;
			}
		}
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println(floorManager.toString(floor));
			printMenu();
			System.out.print("Your move:");
			int move = Integer.parseInt(sc.nextLine());
			floor[cur.i][cur.j] = '.';
			cur = move(move, cur);
			floor[cur.i][cur.j] = 'P';
			int randomCounter = randomCounter();
			System.out.println("Random Counter: "+ randomCounter);
			
		}
		

	}
	
	private static void printMenu(){
		System.out.println("1. Move up");
		System.out.println("2. Move down");
		System.out.println("3. Move left");
		System.out.println("4. Move right");
	}
	
	private static Position move(int move,Position cur){
		int x = cur.i;
		int y = cur.j;
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
		}
		return new Position(x,y);
	}
	
	private static int randomCounter(){
		Random rand = new Random();
		return rand.nextInt(10);
	}

}
