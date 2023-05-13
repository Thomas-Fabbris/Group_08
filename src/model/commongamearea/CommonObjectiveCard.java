package model.commongamearea;

import java.util.Stack;

import model.Player;
import model.personalgamearea.CommonGoals;

public class CommonObjectiveCard {
	
	private int cardId;
	private CommonGoals relatedCommonGoal;
	public static int MAX_CARD_ID = 12;
	private Stack<PointTile> pointTiles = new Stack<>();
	
	public CommonObjectiveCard(int card_id, int numberOfPlayers) {
		this.cardId = card_id;
		
		initRelatedCommonGoal();
		
		initPointsList(numberOfPlayers);
	}
	
	public int getId() {
		return this.cardId;
	}
	
	private void initPointsList(int numberOfPlayers) {
		
		switch (numberOfPlayers) {
		case 2:
			pointTiles.push(new PointTile(this, 4));
			pointTiles.push(new PointTile(this, 8));
			return;

		case 3:
			pointTiles.push(new PointTile(this, 4));
			pointTiles.push(new PointTile(this, 6));
			pointTiles.push(new PointTile(this, 8));
			return;
		
		case 4:
			
			pointTiles.push(new PointTile(this, 2));
			pointTiles.push(new PointTile(this, 4));
			pointTiles.push(new PointTile(this, 6));
			pointTiles.push(new PointTile(this, 8));
			return;
			
		default:
			throw new IllegalStateException("Something went wrong while initializing common objective card with id " + this.cardId);
		}
	}
	
	private void initRelatedCommonGoal() {
		switch (this.getId()) {
		
			case 1:
				this.relatedCommonGoal = CommonGoals.CommonGoal1;
				break;
				
			case 2:
				this.relatedCommonGoal = CommonGoals.CommonGoal2;
				break;
				
			case 3:
				this.relatedCommonGoal = CommonGoals.CommonGoal3;
				break;
				
			case 4:
				this.relatedCommonGoal = CommonGoals.CommonGoal4;
				break;
				
			case 5:
				this.relatedCommonGoal = CommonGoals.CommonGoal5;
				break;
				
			case 6:
				this.relatedCommonGoal = CommonGoals.CommonGoal6;
				break;
				
			case 7:
				this.relatedCommonGoal = CommonGoals.CommonGoal7;
				break;
				
			case 8:
				this.relatedCommonGoal = CommonGoals.CommonGoal8;
				break;
				
			case 9:
				this.relatedCommonGoal = CommonGoals.CommonGoal9;
				break;
				
			case 10:
				this.relatedCommonGoal = CommonGoals.CommonGoal10;
				break;
				
			case 11:
				this.relatedCommonGoal = CommonGoals.CommonGoal11;
				break;
				
			case 12:
				this.relatedCommonGoal = CommonGoals.CommonGoal12;
				break;
				
			default:
				throw new IllegalStateException("Something went wrong while initializing common objective card with id " + this.cardId);
		}
	}
	
	/**
	 * Give the points displayed on the first available tile to the player
	 * @param player to give the points to
	 */
	public void award(Player player) {		
		//If there are no points left, don't give any
		if(pointTiles.size() <= 0) {
			return;
		}
		
		//Give points and remove the value of points awarded to the player
		player.awardPointTile(pointTiles.lastElement());
		pointTiles.pop();
	}

	public Stack<PointTile> getPointTiles() {
		return pointTiles;
	}

	/**
	 * @return the relatedCommonGoal
	 */
	public CommonGoals getRelatedCommonGoal() {
		return relatedCommonGoal;
	}
}
