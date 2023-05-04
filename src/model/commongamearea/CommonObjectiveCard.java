package model.commongamearea;

import java.util.Stack;

import model.personalgamearea.PointTile;
import model.shared.Player;

public class CommonObjectiveCard {
	
	private int card_id;
	public static int MAX_CARD_ID = 12;
	private Stack<PointTile> point_tiles = new Stack<>();
	
	public CommonObjectiveCard(int card_id) {
		this.card_id = card_id;
		
		initPointsList();
	}
	
	public int getId() {
		return this.card_id;
	}
	
	private void initPointsList() {
		
		switch (Player.getNumberOfPlayers()) {
		case 2:
			point_tiles.push(new PointTile(this, 4));
			point_tiles.push(new PointTile(this, 8));
			return;

		case 3:
			point_tiles.push(new PointTile(this, 4));
			point_tiles.push(new PointTile(this, 6));
			point_tiles.push(new PointTile(this, 8));
			return;
		
		case 4:
			
			point_tiles.push(new PointTile(this, 2));
			point_tiles.push(new PointTile(this, 4));
			point_tiles.push(new PointTile(this, 6));
			point_tiles.push(new PointTile(this, 8));
			return;
			
		default:
			throw new IllegalStateException("Something went wrong while initializing common objective card with id " + this.card_id);
		}
	}
	
	/**
	 * Give the points displayed on the first available tile to the player
	 * @param player to give the points to
	 */
	public void award(Player player) {		
		//If there are no points left, don't give any
		if(point_tiles.size() <= 0) {
			return;
		}
		
		//Give points and remove the value of points awarded to the player
		player.awardPointTile(point_tiles.lastElement());
		point_tiles.pop();
	}
	
	//Check if player can receive points - needs to be implemented
	private boolean checkPoints(Player player) {
		return true;
	}
}
