package model;

import model.commongamearea.Board;
import model.commongamearea.CommonObjectiveCard;
import model.commongamearea.GameEndTile;

public class CommonGameArea {

	Board board;
	CommonObjectiveCard card1;
	CommonObjectiveCard card2;
	GameEndTile gameEndTile;
	
	public CommonGameArea() {
		this.board = new Board();
		this.gameEndTile = new GameEndTile(board);
		//TODO: create cards with a random id
	}
	
}
