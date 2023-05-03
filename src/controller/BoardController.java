package controller;

import model.commongamearea.Board;
import view.commongamearea.BoardLabel;
import view.commongamearea.BoardTileLabel;

public class BoardController {
	
	Board board;
	BoardLabel boardLabel;
	
	public BoardController(Board board, BoardLabel boardLabel) {
		this.board = board;
		this.boardLabel = boardLabel;
	}
	
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
