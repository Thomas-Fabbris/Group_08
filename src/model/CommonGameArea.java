package model;

import model.commongamearea.Board;
import model.commongamearea.CommonObjectiveCard;
import model.commongamearea.GameEndTile;
import model.shared.IdGenerator;

public class CommonGameArea {

	private Board board;
	private CommonObjectiveCard[] commonObjectiveCards;
	private GameEndTile gameEndTile;
	private boolean[][] currentBlockedTiles;
	
	/**
	 * This is the constructor of the CommonGameArea class
	 *  
	 * @param numberOfPlayers which will take part of the game
	 */
	public CommonGameArea(int numberOfPlayers) {
		this.board = new Board(numberOfPlayers);
		this.gameEndTile = new GameEndTile(board);
		currentBlockedTiles = board.getCurrentBlockedTiles();

		IdGenerator idGenerator = new IdGenerator();
		
		this.commonObjectiveCards = new CommonObjectiveCard[] {
				new CommonObjectiveCard(idGenerator, numberOfPlayers),
				new CommonObjectiveCard(idGenerator, numberOfPlayers)
		};
	}
	
	/**
	 * This method is a getter
	 * 
	 * @return this.board
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * This method is a getter
	 * 
	 * @return commonObjectiveCards
	 */
	public CommonObjectiveCard[] getCommonObjectiveCards() {
		return commonObjectiveCards;
	}
	
	/**
	 * This method is a getter
	 * 
	 * @return currentBlockedTiles
	 */
	public boolean[][] getCurrentBlockedTiles() {
		return currentBlockedTiles;
	}

	/**
	 * This method returns a boolean that indicates whether the tile had at least one free side,
	 * by observing her position at the beginning of the round,
	 * 
	 * @param row of the tile to be checked
	 * @param column of the tile to be checked
	 * @return boolean (true/false)
	 */
	public boolean isTileFree(int row, int column) {
		return currentBlockedTiles[row][column];
	}

	/**
	 * This method calculates the current blocked tiles array
	 */
	public void updateCurrentBlockedTiles() {
		this.currentBlockedTiles = board.getCurrentBlockedTiles();
	}
	
	/**
	 * This method is a getter
	 * @return gameEndTile
	 */
	public GameEndTile getGameEndTile() {
		return gameEndTile;
	}
}
