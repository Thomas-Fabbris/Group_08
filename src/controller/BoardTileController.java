package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;

import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.commongamearea.BoardTileLabel;

public class BoardTileController implements MouseListener {

	private Board board;
	private BoardTile tile;
	private BoardTileLabel label;
	private BoardTile[][] boardTiles;
	private CommonGameAreaFrame commonGameAreaFrame;
	private MainController mainController;

	public BoardTileController(Board board, BoardTile tile, BoardTileLabel label, BoardTile[][] boardTiles,
			CommonGameAreaFrame commonGameAreaFrame, MainController mainController) {
		this.board = board;
		this.tile = tile;
		this.label = label;
		this.boardTiles = boardTiles;
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.mainController = mainController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (!this.tile.isInteractible()) {
			return;
		}

		if (this.tile.canBePickedUp()) {
			List<BoardTile> selectedTiles = mainController.getCurrentPlayer().getSelectedTiles();

			switch (selectedTiles.size() + 1) {
			case 1:
				commonGameAreaFrame.getSelectedTile1().setIcon(label.getIcon());
				break;

			case 2:
				commonGameAreaFrame.getSelectedTile2().setIcon(label.getIcon());
				break;

			case 3:
				commonGameAreaFrame.getSelectedTile3().setIcon(label.getIcon());
				break;

			default:
				return;
			}
			
			selectedTiles.add(tile);
			this.tile.setActive(false);
			this.label.setVisible(false);

		} else {

			ImageIcon ogIcon = (ImageIcon) this.label.getIcon();
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
	public void globalCheckIfTilesCanBePickedUp() {

		BoardTileLabel[][] tileLabels = commonGameAreaFrame.getBoardTiles();

		for (int i = 0; i < board.getTiles().length; i++) {
			for (int j = 0; j < board.getTiles().length; j++) {
				if (!(board.getTiles()[i][j].canBePickedUp())) {
					ImageIcon ogIcon = (ImageIcon) tileLabels[i][j].getIcon();
					ImageIcon greyIcon = ImageUtils.getGrayImage(ogIcon);
					tileLabels[i][j].setIcon(greyIcon);
				}
			}
		}
	}

}
