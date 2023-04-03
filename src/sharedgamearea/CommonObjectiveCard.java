package sharedgamearea;

import main.Card;

public class CommonObjectiveCard extends Card {
	
	public CommonObjectiveCard(int card_id) {
		super(card_id);
//		this.label = new CommonObjectiveCardLabel(card_id);
//		CommonObjectiveCardContainer.getInstance().registerCard(this.label);
//		CommonObjectiveCardContainer.getInstance().setVisible(true);
	}
	
	public int getId() {
		return this.card_id;
	}
}
