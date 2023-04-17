package personalgamearea;

import main.Tile;
import main.Player;

public class Shelf {
	
	public static int SHELF_HEIGHT = 5;
	public static int SHELF_WIDTH = 6;
	
	Player player;
	Tile[][] shelfTyle = new Tile[SHELF_HEIGHT][SHELF_WIDTH];
	
	public Shelf() {
		
	}
	
	public void dropTile(Tile tile, int column) {
		
	}

	public Object getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
