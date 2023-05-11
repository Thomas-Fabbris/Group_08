package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.shared.TileType;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.commongamearea.BoardTileLabel;

public class BoardTileController implements MouseListener {

	Board board;
	BoardTile tile;
	BoardTileLabel label;
	BoardTile[][] boardTiles;
	CommonGameAreaFrame commonGameAreaFrame;
	
	public BoardTileController(Board board, BoardTile tile, BoardTileLabel label, BoardTile[][] boardTiles, CommonGameAreaFrame commonGameAreaFrame) {
		this.board = board;
		this.tile = tile;
		this.label = label;
		this.boardTiles = boardTiles;
		this.commonGameAreaFrame = commonGameAreaFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {	
		
		if (!this.tile.isInteractible()) {
			return;
		}
		
		if (this.tile.canBePickedUp(tile)) {
			/*
			 * TODO select tile and bring it to the personal game area
			 * TODO to be placed in the personal shelf of the player
			 */
		} else {
			
			ImageIcon ogIcon = (ImageIcon)this.label.getIcon();
			ImageIcon greyIcon = ImageUtils.getGrayImage(ogIcon);
			this.label.setIcon(greyIcon);
			
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
	
	/* 
	 * shows by using colors if the selected tiles can be picked up
	 */
	public void globalCheckIfTilesCanBePickedUp () {
		
		BoardTileLabel[][] tileLabels = commonGameAreaFrame.getBoardTiles();
		
		for (int i = 0; i < board.getTiles().length; i++) {
			for (int j = 0; j < board.getTiles().length; j++) {
				if (!(board.getTiles()[i][j].canBePickedUp(board.getTiles()[i][j]))){
					ImageIcon ogIcon = (ImageIcon)tileLabels[i][j].getIcon();
					ImageIcon greyIcon = ImageUtils.getGrayImage(ogIcon);
					tileLabels[i][j].setIcon(greyIcon);
				}
			}
		}
	}

}
