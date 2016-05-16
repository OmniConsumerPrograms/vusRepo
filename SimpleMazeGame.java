

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

/*
 *  Build a Maze game. 
 *
 *  This implmentation does not use any design patterns. 
 *  Compare the createMaze() method of this class to the ccorresponding method of the following 
 *  classes which create the same Maze using different creational design patterns. 
 *   - MazeGameAbstractFactory: using Abstract Factory design pattern
 *   - MazeGameBuilder: using Builder design pattern
 *
 *  Run the program as follows:
 *   java maze.SimpleMazeGame Large 
 *         -- create a large Maze game
 *   java maze.SimpleMazeGame 
 *         -- create a small Maze game
 */
public class SimpleMazeGame { 

  /**
   *  Creates a small maze with 2 rooms. 
   */ 
  public static Maze createMaze() { 
    Maze maze = new Maze(); 
    Room room1 = new Room(1); 
    Room room2 = new Room(2); 
    Door door = new Door(room1, room2);

    room1.setSide(Direction.NORTH, new Wall());
    room1.setSide(Direction.EAST, door);
    room1.setSide(Direction.SOUTH, new Wall());
    room1.setSide(Direction.WEST, new Wall());
    
    room2.setSide(Direction.NORTH, new Wall());
    room2.setSide(Direction.EAST, new Wall());
    room2.setSide(Direction.SOUTH, new Wall());
    room2.setSide(Direction.WEST, door);

    maze.addRoom(room1);
    maze.addRoom(room2);

    return maze; 
  }

  /**
   *  Creates a large maze with 9 rooms. 
 * @throws FileNotFoundException 
   */ 
  public static Maze createLargeMaze() throws FileNotFoundException { 
  	RoomText[] roomTexts = TextFileManager.getRoomTexts("floor1.txt");
  	int width = 3;
  	DoorRoomState[][] maze2 = new DoorRoomState[roomTexts.length][4]; // 4 = no of Directions(N,W, S, E)
	  
		
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
		
	Maze maze = new Maze(); 
	/*
	 * Create Room
	 */
	Room[] rooms = new Room[roomTexts.length];
	for(int m = 1; m <= roomTexts.length; m++){
		rooms[m-1] = new Room(m);
	}

	//Set side
	for(int m = 0; m< maze2.length; m++)
	{
		for(int n = 0; n< maze2[m].length;n++){
			DoorRoomState dRS = maze2[m][n];
			
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
				//int otherRoomNo = dRS.getOtherRoom()-1;
				Door d = new Door(rooms[m],rooms[dRS.getOtherRoom()-1]);
				//Set Open or Close Door
				if(dRS.getState() == 126)
				{
					d.setOpen(true);
				}else if(dRS.getState() == 120)
				{
					d.setOpen(false);
				}
				
				if(n == 0)	
					rooms[m].setSide(Direction.NORTH, d); 
				else if(n == 1)
					rooms[m].setSide(Direction.EAST, d);
				else if(n == 2)
					rooms[m].setSide(Direction.SOUTH, d);
				else 
					rooms[m].setSide(Direction.WEST, d);
				
			}
		}
	} // End For
	//Add maze
	
	for(int m = 1; m <= roomTexts.length; m++){
		maze.addRoom(rooms[m-1]);
	}  
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

    return maze;*/
	  
	return maze;
  }

  public static void main(String[] args) throws FileNotFoundException { 
    Maze maze; 
    if (args.length > 0 &&
	"Large".equals(args[0])) { 
      maze = createLargeMaze();       
    } else {
      maze = createMaze(); 
    }
    maze.setCurrentRoom(1); 
    
    JFrame frame;    
    frame = new JFrame("Maze");
    frame.setContentPane(new Maze.MazePanel(maze)); 
    frame.pack();
    Dimension frameDim = frame.getSize(); 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - frameDim.width / 2, 
		      screenSize.height / 2 - frameDim.height / 2);    
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    frame.setVisible(true);
  }

}
