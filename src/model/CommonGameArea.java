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
	private boolean[][] currentBlockedTiles;

	public CommonGameArea(int numberOfPlayers) {
		this.board = new Board(numberOfPlayers);
		this.gameEndTile = new GameEndTile(board);
		currentBlockedTiles = board.getCurrentBlockedTiles();

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

	public boolean[][] getCurrentBlockedTiles() {
		return currentBlockedTiles;
	}

	/**
	 * Returns whether, at the start of the round, the tile had at least one free side 
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isTileFree(int row, int column) {
		return currentBlockedTiles[row][column];
	}

	/**
	 * Calculates the current blocked tiles array
	 */
	public void updateCurrentBlockedTiles() {
		this.currentBlockedTiles = board.getCurrentBlockedTiles();
	}
}
