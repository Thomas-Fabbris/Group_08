package model.personalgamearea;

import model.commongamearea.CommonObjectiveCard;
import model.shared.Tile;
import model.shared.TileType;

public class PointTile extends Tile {
	
	private int card_id = -1; //default value -1 means no point tile has been received yet
	private int points;
	
	public PointTile(CommonObjectiveCard card, int points) {
		super(TileType.POINTS);
		this.card_id = card.getId();
		this.points = points;
	}
	
	public int getCardId() {
		return card_id;
	}

	public void setCardId(int card_id) {
		this.card_id = card_id;
	}
	
	public int getPoints() {
		return points;
	}
}