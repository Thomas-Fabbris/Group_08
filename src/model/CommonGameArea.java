package model;

import model.commongamearea.Board;
import model.commongamearea.CommonObjectiveCard;
import model.commongamearea.GameEndTile;
import model.shared.IdGenerator;

public class CommonGameArea {

	private Board board;
	private CommonObjectiveCard card1;
	private CommonObjectiveCard card2;
	private GameEndTile gameEndTile;

	
	public CommonGameArea(int numberOfPlayers) {
		this.board = new Board(numberOfPlayers);
		this.gameEndTile = new GameEndTile(board);

		
		IdGenerator idGenerator = new IdGenerator();
		this.card1 = new CommonObjectiveCard(idGenerator.getNewCommonObjectiveCardId(), numberOfPlayers);
		this.card2 = new CommonObjectiveCard(idGenerator.getNewCommonObjectiveCardId(), numberOfPlayers);
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public CommonObjectiveCard getCard1() {
		return this.card1;
	}
	
	public CommonObjectiveCard getCard2() {
		return this.card2;
	}	
}
