package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.shared.TileType;
import view.commongamearea.BoardTileLabel;

public class BoardTileController implements MouseListener {

	Board board;
	BoardTile tile;
	BoardTileLabel label;
	BoardTile[][] boardTiles;
	
	public BoardTileController(Board board, BoardTile tile, BoardTileLabel label, BoardTile[][] boardTiles) {
		this.board = board;
		this.tile = tile;
		this.label = label;
		this.boardTiles = boardTiles;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		//If the tile is not active, don't do anything
		if(!tile.isActive())
			return;
		
		if(tile.getType() == TileType.BOOKS) {
			System.out.println("clicked on books");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

}
