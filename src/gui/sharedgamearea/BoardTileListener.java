package gui.sharedgamearea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.TileType;
import sharedgamearea.Board;
import sharedgamearea.BoardTile;

public class BoardTileListener implements MouseListener {
	private Board board;
	private BoardTile tile;
	public BoardTileListener (Board board, BoardTile tile) {
		this.board = board;
		this.tile = tile;
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		tile.getLabel().setIcon(tile.getLabel().imageGray); //TODO: remove, used for debug
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
