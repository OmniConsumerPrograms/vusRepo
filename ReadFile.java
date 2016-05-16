import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) throws FileNotFoundException{
		String fileName = "floor1.txt";
		Scanner sc = new Scanner(new File(fileName));
		int width = Integer.parseInt(sc.nextLine()); 
		
		// 0 - 48, # - 35, . - 46 , ~ - 126, x - 120
		
		int[][] maze = new int[width*2+1][width*2+1];
		
		int i = 0 ;
		while(sc.hasNext()){
			String s = sc.nextLine();
			for(int j = 0; j < maze.length; j++){
				maze[i][j] = s.charAt(j);
				
			}
			i++;
			
			
		}
		
		for(i = 0; i < maze.length; i++){
			for(int j = 0; j < maze.length; j++){
				System.out.print(maze[i][j] +" ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		RoomText[] roomTexts = new RoomText[width*width];
		
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
		
		for(int k = 0; k < roomTexts.length; k++){
			RoomText rT = roomTexts[k];
			System.out.println(rT.getNorth() +" "+rT.getEast()+" "+rT.getSouth()+" "+rT.getWest());
		
		}
		DoorRoomState[][] maze2 = new DoorRoomState[width*width][4];
		
		for(int m = 0; m< maze2.length; m++){
			RoomText rT = roomTexts[m];
			for(int n = 0; n < maze2[m].length; n++){
				switch (n){
					case 0: // North
						if(rT.getNorth() == 126)
							//maze2[m][n] = m+width + 1;
							maze2[m][n] = new DoorRoomState(m+width + 1,126);
						else if(rT.getNorth() == 120){
							//maze2[m][n] = m+width + 1;
							maze2[m][n] = new DoorRoomState(m+width + 1,120);
						}else
							//maze2[m][n] = 0;
							maze2[m][n] = null;
						break;
					case 1: // East
						if(rT.getEast() == 126)
							//maze2[m][n] = m-1 + 1;
							maze2[m][n] = new DoorRoomState(m-1 + 1,126);
						else if(rT.getEast() == 120){
							//maze2[m][n] = m-1 + 1;
							maze2[m][n] = new DoorRoomState(m-1 + 1,120);
						}else
							//maze2[m][n] = 0;
							maze2[m][n] = null;
						
						break;
					case 2: // South
						if(rT.getSouth() == 126)
							//maze2[m][n] = m-width + 1 ;
							maze2[m][n] = new DoorRoomState(m-width + 1,126);
						else if(rT.getSouth() == 120){
							//maze2[m][n] = m-width + 1;
							maze2[m][n] = new DoorRoomState(m-width + 1,120);
						}else
							//maze2[m][n] = 0;
							maze2[m][n] =  null;
						
						break;
					case 3: //West
						if(rT.getWest() == 126)
							//maze2[m][n] = m+1 + 1 ;
							maze2[m][n] = new DoorRoomState(m+1 + 1,126);
						else if(rT.getWest() == 120){
							//maze2[m][n] = m+1 + 1;
							maze2[m][n] = new DoorRoomState(m+1 + 1,120);
						}else
							//maze2[m][n] = 0;
							maze2[m][n] = null;
					
						break;
					default:
						break;
				}
			}
		} //end for
		
		int count = 0;
		for(int m = 0; m< maze2.length; m++){
			for(int n = 0; n< maze2[m].length;n++){
				DoorRoomState dRS = maze2[m][n];
				if(dRS != null && dRS.getOtherRoom() > m){
					System.out.println("Pair: "+(m+1)+" "+dRS.getOtherRoom());
					count++;
				}
				//System.out.print(maze2[m][n] +" ");
				
			}
			//System.out.println();
		}
		
		
		Maze simpleMaze = new Maze(); 
		//Create Room
		Room[] rooms = new Room[roomTexts.length];
		for(int m = 1; m <= roomTexts.length; m++){
			rooms[m-1] = new Room(m);
		}
		
		
		//Create Door and setOpen
		Door[] doors = new Door[count];
		for(int m = 1; m<=count; m++){
			//doors[m-1] = new Door(rooms[1],rooms[2]);
		}
		
		count = 0;
		for(int m = 0; m< maze2.length; m++){
			for(int n = 0; n< maze2[m].length;n++){
				DoorRoomState dRS = maze2[m][n];
				if(dRS != null && dRS.getOtherRoom() > m){
					//System.out.println("Pair: "+(m+1)+" "+dRS.getOtherRoom());
					doors[count] = new Door(rooms[m],rooms[dRS.getOtherRoom()-1]);
					//doors[count].setOpen(true); 
					if(dRS.getState() == 126){
						doors[count].setOpen(true);
					}else{
						doors[count].setOpen(false);
					}
					count++;
				}
			}
		}
		
		//Set side
		for(int m = 0; m< maze2.length; m++){
			for(int n = 0; n< maze2[m].length;n++){
				DoorRoomState dRS = maze2[m][n];
				int otherRoomNo = dRS.getOtherRoom()-1;
				if(dRS == null){
					if(n == 0)	
						rooms[m].setSide(Direction.NORTH, new Wall()); 
					else if(n == 1)
						rooms[m].setSide(Direction.EAST, new Wall()); 
					else if(n == 2)
						rooms[m].setSide(Direction.SOUTH, new Wall()); 
					else 
						rooms[m].setSide(Direction.WEST, new Wall()); 
				}else{
					if(n == 0)	
						rooms[m].setSide(Direction.NORTH, doors[otherRoomNo]); 
					else if(n == 1)
						rooms[m].setSide(Direction.EAST, doors[otherRoomNo]);
					else if(n == 2)
						rooms[m].setSide(Direction.SOUTH, doors[otherRoomNo]);
					else 
						rooms[m].setSide(Direction.WEST, doors[otherRoomNo]);
					
				}
			}
		}
		//Add maze
		
		for(int m = 1; m <= roomTexts.length; m++){
			simpleMaze.addRoom(rooms[m-1]);
		}
		System.out.println("Count= "+count);
		/*
		Maze maze = new Maze(); 
	    Room room1 = new Room(1); 
	    Room room2 = new Room(2); 
	    Room room3 = new Room(3); 
	    Room room4 = new Room(4); 
	    Room room5 = new Room(5); 
	    Room room6 = new Room(6); 
	    Room room7 = new Room(7); 
	    Room room8 = new Room(8); 
	    Room room9 = new Room(9); 
	    
	    Room room10 = new Room(10);
	    Room room11 = new Room(11);
	    Room room12 = new Room(12);
	    
	    Door door1 = new Door(room1, room2);
	    Door door2 = new Door(room2, room3);
	    Door door3 = new Door(room4, room5);
	    Door door4 = new Door(room5, room6);
	    Door door5 = new Door(room5, room8);
	    Door door6 = new Door(room6, room9);
	    Door door7 = new Door(room7, room8);
	    Door door8 = new Door(room1, room4);
	    
	    Door door9 = new Door(room10, room11);
	    Door door10 = new Door(room11, room12);
	    Door door11 = new Door(room9, room12);
	    
	    
	    door1.setOpen(true); 
	    door2.setOpen(false); 
	    door3.setOpen(true); 
	    door4.setOpen(true); 
	    door5.setOpen(false); 
	    door6.setOpen(true); 
	    door7.setOpen(true); 
	    door8.setOpen(true); 
	    
	    door9.setOpen(true);
	    door10.setOpen(false); 
	    door11.setOpen(true);

	    room1.setSide(Direction.NORTH, door8);
	    room1.setSide(Direction.EAST, new Wall());
	    room1.setSide(Direction.SOUTH, new Wall());
	    room1.setSide(Direction.WEST, door1);

	    room2.setSide(Direction.NORTH, new Wall());
	    room2.setSide(Direction.EAST, door1);
	    room2.setSide(Direction.SOUTH, new Wall());
	    room2.setSide(Direction.WEST, door2);

	    room3.setSide(Direction.NORTH, new Wall());
	    room3.setSide(Direction.EAST, door2);
	    room3.setSide(Direction.SOUTH, new Wall());
	    room3.setSide(Direction.WEST, new Wall());

	    room4.setSide(Direction.NORTH, new Wall());
	    room4.setSide(Direction.EAST, new Wall());
	    room4.setSide(Direction.SOUTH, door8);
	    room4.setSide(Direction.WEST, door3);

	    room5.setSide(Direction.NORTH, door5);
	    room5.setSide(Direction.EAST, door3);
	    room5.setSide(Direction.SOUTH, new Wall());
	    room5.setSide(Direction.WEST, door4);

	    room6.setSide(Direction.NORTH, door6);
	    room6.setSide(Direction.EAST, door4);
	    room6.setSide(Direction.SOUTH, new Wall());
	    room6.setSide(Direction.WEST, new Wall());

	    room7.setSide(Direction.NORTH, new Wall());
	    room7.setSide(Direction.EAST, new Wall());
	    room7.setSide(Direction.SOUTH, new Wall());
	    room7.setSide(Direction.WEST, door7);

	    room8.setSide(Direction.NORTH, new Wall());
	    room8.setSide(Direction.EAST, door7);
	    room8.setSide(Direction.SOUTH, door5);
	    room8.setSide(Direction.WEST, new Wall());

	    room9.setSide(Direction.NORTH, door11);
	    room9.setSide(Direction.EAST, new Wall());
	    room9.setSide(Direction.SOUTH, door6);
	    room9.setSide(Direction.WEST, new Wall());
	    
	    room10.setSide(Direction.NORTH, new Wall());
	    room10.setSide(Direction.EAST, new Wall());
	    room10.setSide(Direction.SOUTH, new Wall());
	    room10.setSide(Direction.WEST, door9);
	    
	    room11.setSide(Direction.NORTH, new Wall());
	    room11.setSide(Direction.EAST, door9);
	    room11.setSide(Direction.SOUTH, new Wall());
	    room11.setSide(Direction.WEST, door10);
	    
	    room12.setSide(Direction.NORTH, new Wall());
	    room12.setSide(Direction.EAST, door10);
	    room12.setSide(Direction.SOUTH, door11);
	    room12.setSide(Direction.WEST, new Wall());

	    maze.addRoom(room1);
	    maze.addRoom(room2);
	    maze.addRoom(room3);
	    maze.addRoom(room4);
	    maze.addRoom(room5);
	    maze.addRoom(room6);
	    maze.addRoom(room7);
	    maze.addRoom(room8);
	    maze.addRoom(room9);
	    maze.addRoom(room10);
	    maze.addRoom(room11);
	    maze.addRoom(room12);
	    */
		
	}
}

class DoorRoomState{
	protected int otherRoom ; 
	protected int state; // Close or Open
	
	public DoorRoomState(int otherRoom, int state) {
		
		this.otherRoom = otherRoom;
		this.state = state;
	}
	public int getOtherRoom() {
		return otherRoom;
	}
	public void setOtherRoom(int otherRoom) {
		this.otherRoom = otherRoom;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "DoorRoomState [otherRoom=" + otherRoom + ", state=" + state + "]";
	}
	
	
}



