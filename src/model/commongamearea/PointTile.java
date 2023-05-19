package model.commongamearea;

import model.shared.Tile;
import model.shared.TileType;

public class PointTile extends Tile {
	
	private int cardId = -1; //default value -1 means this point tile is not associated with a card yet
	private int romanNumber;
	private int points;
	
	public PointTile(CommonObjectiveCard card, int points) {
		super(TileType.POINTS);
		this.cardId = card.getId();
		this.romanNumber = card.getRomanNumber();
		this.points = points;
	}
	
	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	public int getPoints() {
		return points;
	}

	public int getRomanNumber() {
		return romanNumber;
	}
}
