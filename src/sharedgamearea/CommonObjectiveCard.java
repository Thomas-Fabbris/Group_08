package sharedgamearea;

import gui.CommonObjectiveCardLabel;
import gui.CommonObjectiveCardWindow;
import main.Card;

public class CommonObjectiveCard extends Card {
	
	public CommonObjectiveCard(int card_id) {
		super(card_id);
		this.label = new CommonObjectiveCardLabel(card_id);
		CommonObjectiveCardWindow.getInstance().registerCard(this.label);
		CommonObjectiveCardWindow.getInstance().setVisible(true);
	}
}
