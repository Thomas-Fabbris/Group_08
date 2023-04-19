package personalgamearea;

import gui.PointTileLabel;
import sharedgamearea.CommonObjectiveCard;

public class PointTile {
	
	private int card_id = -1; //default value -1 means no point tile has been received yet
	private int points;
	private PointTileLabel label;
	
	public PointTile(CommonObjectiveCard card, int points) {
		this.card_id = card.getId();
		this.points = points;
	}
	
	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public int getPoints() {
		return points;
	}

	public PointTileLabel getLabel() {
		return label;
	}

	public void setLabel(PointTileLabel label) {
		this.label = label;
	}
}
