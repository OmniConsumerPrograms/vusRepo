

public class Floor {
	private int floorID;
	private int[][] floor;
	
	public Floor(int floorID, int[][] floor) 
	{
		this.floorID = floorID;
		this.floor = floor;
	}

	public int getFloorID() {
		return floorID;
	}

	public void setFloorID(int floorID) {
		this.floorID = floorID;
	}

	public int[][] getFloor() {
		return floor;
	}

	public void setFloor(int[][] floor) {
		this.floor = floor;
	}
	
	

}
