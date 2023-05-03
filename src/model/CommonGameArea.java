package model;

import model.commongamearea.Board;
import model.commongamearea.CommonObjectiveCard;
import model.commongamearea.GameEndTile;
import model.shared.IdGenerator;

public class CommonGameArea {

	Board board;
	CommonObjectiveCard card1;
	CommonObjectiveCard card2;
	GameEndTile gameEndTile;
	
	public CommonGameArea() {
		this.board = new Board();
		this.gameEndTile = new GameEndTile(board);
		
		IdGenerator idGenerator = new IdGenerator();
		this.card1 = new CommonObjectiveCard(idGenerator.getNewCommonObjectiveCardId());
		this.card2 = new CommonObjectiveCard(idGenerator.getNewCommonObjectiveCardId());
	}
	
}
