package controller.commongamearea;

import javax.swing.JLabel;

import model.commongamearea.Board;
import view.commongamearea.BoardTileLabel;

public class BoardController {
	
	private Board board;
	private JLabel boardLabel;
	
	public BoardController(Board board, JLabel boardLabel) {
		this.board = board;
		this.boardLabel = boardLabel;
	}
	
	/**
	 * Adds BoardTileLabels to each boardTile
	 */
	public void addTiles() {
		for (int i = 0; i < board.getValidPositions().length; i++) {
			for (int j = 0; j < board.getValidPositions().length; j++) {
				if(board.getValidPositions()[i][j]) {					
					boardLabel.add(new BoardTileLabel(board.getTileType(i, j), i, j, boardLabel.getSize()));
				}
			}
		}
	}
	
}
