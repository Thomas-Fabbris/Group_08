package sharedgamearea;

import gui.CommonObjectiveCardLabel;
import gui.SharedGameAreaWindow;
import main.Card;

public class CommonObjectiveCard extends Card {
	
	public CommonObjectiveCard(int card_id) {
		super(card_id);
		this.label = new CommonObjectiveCardLabel(card_id);
	}
	
	public int getId() {
		return this.card_id;
	}
}
