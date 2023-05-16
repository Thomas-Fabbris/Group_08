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
	private ImageIcon coloredIcon;
	private ImageIcon grayIcon;
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

		mainController.updateBoardTileLabel(tile, label);
		this.coloredIcon = (ImageIcon) label.getIcon();
		this.grayIcon = ImageUtils.getGrayImage(coloredIcon);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (!this.tile.isInteractible()) {
			return;
		}

		if (this.tile.canBePickedUp()) {
			List<BoardTile> selectedTiles = mainController.getCurrentPlayer().getSelectedTiles();

			switch (selectedTiles.size()) {
			case 0:
				pickupFirstTile(selectedTiles);
				break;

			case 1:
				pickupSecondTile(selectedTiles);
				break;

			case 2:
				pickupThirdTile(selectedTiles);
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!this.tile.canBePickedUp()) {
			this.label.setIcon(this.grayIcon);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.label.setIcon(this.coloredIcon);
	}

	/**
	 * Picks up the selected tile
	 * 
	 * @param selectedTiles
	 */
	private void pickupFirstTile(List<BoardTile> selectedTiles) {
		// If no tiles have been selected, pick up the current tile
		commonGameAreaFrame.getSelectedTile1().setIcon(label.getIcon());
		selectedTiles.add(tile);
		this.tile.setActive(false);
		this.label.setVisible(false);
	}

	/**
	 * Checks whether the selected tile can be picked up. The tile must be adjacent
	 * to the previous selected tile.
	 * 
	 * @param selectedTiles
	 */
	private void pickupSecondTile(List<BoardTile> selectedTiles) {
		// If one tile has been selected, the current tile can be picked up if it is
		// adjacent
		if (this.tile.isAdjacent(selectedTiles.get(0))) {
			commonGameAreaFrame.getSelectedTile2().setIcon(label.getIcon());
			selectedTiles.add(tile);
			this.tile.setActive(false);
			this.label.setVisible(false);
		} else {
			this.label.setIcon(grayIcon);
		}
	}

	/**
	 * Checks whether the selected tile can be picked up. Uses vector arithmetic to
	 * predict the coordinates of the third tile, and it checks whether they match
	 * the selected tile.
	 * 
	 * @param selectedTiles
	 * @param row           row that has to match (0 means ignore)
	 * @param column        column that has to match (0 means ignore)
	 */
	private void pickupThirdTile(List<BoardTile> selectedTiles) {
		int Vx = selectedTiles.get(1).getRow() - selectedTiles.get(0).getRow();
		int Vy = selectedTiles.get(1).getColumn() - selectedTiles.get(0).getColumn();

		int row = selectedTiles.get(1).getRow() + Vx;
		int column = selectedTiles.get(1).getColumn() + Vy;

		if (this.tile.getRow() == row && this.tile.getColumn() == column) {
			commonGameAreaFrame.getSelectedTile3().setIcon(label.getIcon());
			selectedTiles.add(tile);
			this.tile.setActive(false);
			this.label.setVisible(false);
		} else {
			this.label.setIcon(grayIcon);
		}
	}

	/*
	 * shows by using colours if the selected tiles can be picked up
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
