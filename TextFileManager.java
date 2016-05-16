import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileManager {
	public static RoomText[] getRoomTexts(String fileName) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(fileName));
		int width = Integer.parseInt(sc.nextLine()); 
		RoomText[] roomTexts = new RoomText[width*width];
		int[][] maze = new int[width*2+1][width*2+1];
		int i = 0 ;
		while(sc.hasNext()){
			String s = sc.nextLine();
			for(int j = 0; j < maze.length; j++){
				maze[i][j] = s.charAt(j);
				
			}
			i++;
		}
		
		int startx = 1, starty=1;
		int roomTextsIndex = roomTexts.length-1;
		for(int k = 0 ; k <width;k++){
			for(int l = 0 ; l <width;l++){
				//System.out.print(maze[startx][starty] + " ");
				int north = maze[startx-1][starty];
				int east = maze[startx ][starty+1];
				int south = maze[startx+1][starty];
				int west = maze[startx][starty-1];
				
				RoomText rT = new RoomText(north,east,south,west);
				roomTexts[roomTextsIndex] = rT;
				roomTextsIndex--;
				starty += 2; 
			}
			startx += 2;
			starty = 1;
		}
		return roomTexts;
	}
}
class RoomText{
	protected int north;
	protected int east;
	protected int south;
	protected int west;
	protected RoomText(int north, int east, int south, int west) {
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	public int getNorth() {
		return north;
	}
	public void setNorth(int north) {
		this.north = north;
	}
	public int getEast() {
		return east;
	}
	public void setEast(int east) {
		this.east = east;
	}
	public int getSouth() {
		return south;
	}
	public void setSouth(int south) {
		this.south = south;
	}
	public int getWest() {
		return west;
	}
	public void setWest(int west) {
		this.west = west;
	}
	
	
	
}