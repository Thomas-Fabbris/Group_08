package sharedgamearea;

import java.util.ArrayList;
import java.util.List;

import main.Tile;
import main.TileType;

public class PointTile extends Tile {
	
	//2 players -> 4,8 - 3 players -> 4,6,8 - 4 players -> 2,4,6,8
	
	private List<Integer> points_steps = new ArrayList<Integer>();
	
	public PointTile() {
		super(TileType.POINTS);
//		Init(number_of_players);
	}
	
	private void Init(int number_of_players) {
		switch(number_of_players) {
		case 2:
			points_steps.add(4);
			points_steps.add(8);
			break;
		
		case 3:
			points_steps.add(4);
			points_steps.add(6);
			points_steps.add(8);
			break;
			
		case 4:
			points_steps.add(2);
			points_steps.add(4);
			points_steps.add(6);
			points_steps.add(8);
			break;
		}
	}
}
