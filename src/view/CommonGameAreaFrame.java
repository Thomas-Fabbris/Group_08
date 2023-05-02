package view;

import view.sharedgamearea.BoardLabel;
import view.sharedgamearea.CommonObjectiveCardLabel;

public class CommonGameAreaFrame {

	BoardLabel boardLabel;
	CommonObjectiveCardLabel card1;
	CommonObjectiveCardLabel card2;
	
	public CommonGameAreaFrame() {
		this.boardLabel = new BoardLabel(null);
	}
}
