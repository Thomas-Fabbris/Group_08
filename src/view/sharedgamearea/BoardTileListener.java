package view.sharedgamearea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.shared.TileType;

public class BoardTileListener implements MouseListener {
	private Board board;
	private BoardTile tile;
	public BoardTileListener (Board board, BoardTile tile) {
		this.board = board;
		this.tile = tile;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(tile.getLabel().getIcon().equals(tile.getLabel().image))
			tile.getLabel().setIcon(tile.getLabel().imageGray);
		else
			tile.getLabel().setIcon(tile.getLabel().image);		

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
