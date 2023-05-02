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
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
