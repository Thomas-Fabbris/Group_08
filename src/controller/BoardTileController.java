package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;

import model.CommonGameArea;
import model.Player;
import model.commongamearea.BoardTile;
import model.commongamearea.InvalidMoveException;
import model.personalgamearea.IllegalActionException;
import model.shared.TileSides;
import observer.Observer;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.PersonalGameAreaFrame;
import view.commongamearea.BoardTileLabel;

public class BoardTileController implements MouseListener, Observer {

	private BoardTile tile;
	private BoardTileLabel label;
	private ImageIcon coloredIcon;
	private ImageIcon grayIcon;
	private CommonGameArea commonGameArea;
	private CommonGameAreaFrame commonGameAreaFrame;
	private MainController mainController;
	private PersonalGameAreaFrame personalGameAreaFrame;

	public BoardTileController(BoardTile tile, BoardTileLabel label, CommonGameArea commonGameArea,
			CommonGameAreaFrame commonGameAreaFrame, MainController mainController,
			PersonalGameAreaFrame personalGameAreaFrame) {

		if (tile == null) {
			throw new NullPointerException(
					"Warning, tile must not be set to null while creating a BoardTileController instance!");
		}
		if (label == null) {
			throw new NullPointerException(
					"Warning, label must not be set to null while creating a BoardTileController instance!");
		}
		if (commonGameArea == null) {
			throw new NullPointerException(
					"Warning, commonGameArea must not be set to null while creating a BoardTileController instance!");
		}
		if (commonGameAreaFrame == null) {
			throw new NullPointerException(
					"Warning, commonGameAreaFrame must not be set to null while creating a BoardTileController instance!");
		}
		if (mainController == null) {
			throw new NullPointerException(
					"Warning, mainController must not be set to null while creating a BoardTileController instance!");
		}
		if (personalGameAreaFrame == null) {
			throw new NullPointerException(
					"Warning, personalGameAreaFrame must not be set to null while creating a BoardTileController instance!");
		}

		this.tile = tile;
		this.label = label;
		this.commonGameArea = commonGameArea;
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.personalGameAreaFrame = personalGameAreaFrame;
		this.mainController = mainController;

		mainController.updateBoardTileLabel(tile, label);
		updateIcons();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		try {
			this.personalGameAreaFrame.getWarnings().setVisible(false);

			Player currentPlayer = mainController.getCurrentPlayer();
			List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();

			// Does the bookshelf have enough space available to insert the selected tiles?
			if (!currentPlayer.getBookshelf().hasAvaibleSpaceFor(selectedTiles.size()+1)) {
				this.label.setIcon(grayIcon);
				throw new IllegalActionException("Not enough space in the bookshelf!");
			}

			// Has the player already inserted a tile in the bookshelf?
			if (currentPlayer.hasSelectedColumn()) {
				this.label.setIcon(grayIcon);
				throw new IllegalActionException("You cannot pick up tiles after adding one to the bookshelf!");
			}

			// Can the tile be picked up?
			if (!commonGameArea.isTileFree(tile.getRow(), tile.getColumn())) {
				this.label.setIcon(grayIcon);
				throw new IllegalActionException("This tile is blocked!");
			}

			// Use a different check for tile pickup based on the amount of tiles already
			// selected
			switch (selectedTiles.size()) {
			case 0:
				pickupFirstTile(selectedTiles);
				return;

			case 1:
				pickupSecondTile(selectedTiles);
				return;

			case 2:
				pickupThirdTile(selectedTiles);
				return;

			default:
				this.label.setIcon(grayIcon);
				throw new IllegalActionException("You cannot pick up this tile!");
			}
		}

		catch (InvalidMoveException ex) {
			this.personalGameAreaFrame.getWarnings().setText(ex.getMessage());
			this.personalGameAreaFrame.getWarnings().setVisible(true);
			this.label.setIcon(grayIcon);
		} catch (IllegalActionException ex) {
			this.personalGameAreaFrame.getWarnings().setText(ex.getMessage());
			this.personalGameAreaFrame.getWarnings().setVisible(true);
			this.label.setIcon(grayIcon);
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
		this.label.setIcon(this.coloredIcon);
	}

	/**
	 * Updates this.tile, this.label, this.coloredIcon, this.grayIcon
	 */
	@Override
	public void update(Object[] data) {
		BoardTile[][] tiles = (BoardTile[][]) data[0];
		BoardTileLabel[][] tileLabels = (BoardTileLabel[][]) data[1];

		this.tile = tiles[tile.getRow()][tile.getColumn()];
		this.label = tileLabels[tile.getRow()][tile.getColumn()];

		updateIcons();
	}

	private void updateIcons() {
		this.coloredIcon = (ImageIcon) label.getIcon();
		this.grayIcon = ImageUtils.getGrayImage(coloredIcon);
	}

	/**
	 * Picks up the selected tile
	 * 
	 * @param selectedTiles
	 */
	private void pickupFirstTile(List<BoardTile> selectedTiles) {
		// The tile must have at least one free side and max 3 free sides (1, 2 or 3
		// free sides = pickup)
		if (tile.getBlockedSides() != TileSides.AT_LEAST_ONE_SIDE_FREE) {
			this.label.setIcon(grayIcon);
			throw new InvalidMoveException("You cannot pick up this tile!");
		}
		commonGameAreaFrame.getSelectedTile1().setIcon(label.getIcon());
		selectedTiles.add(tile);
		this.tile.disable();
		this.label.setVisible(false);
	}

	/**
	 * Checks whether the selected tile can be picked up. The tile must be adjacent
	 * to the previous selected tile and it must have at least one free side
	 * 
	 * @param selectedTiles
	 */
	private void pickupSecondTile(List<BoardTile> selectedTiles) {
		// If one tile has been selected, the current tile can be picked up if it is
		// adjacent

		if (tile.isAdjacent(selectedTiles.get(0)) && tile.getBlockedSides() != TileSides.ALL_SIDES_BLOCKED) {
			commonGameAreaFrame.getSelectedTile2().setIcon(label.getIcon());
			selectedTiles.add(tile);
			this.tile.setActive(false);
			this.label.setVisible(false);
		} else {
			this.label.setIcon(grayIcon);
			throw new InvalidMoveException("You cannot pick up this tile!");
		}
	}

	/**
	 * Checks whether the selected tile can be picked up. First it finds the vector
	 * that gives the direction between the first two selected tiles, then it
	 * enforces that the third tile has to be either on the same row or on the same
	 * column.
	 * 
	 * @param selectedTiles
	 */
	private void pickupThirdTile(List<BoardTile> selectedTiles) {
		// If Vx = 0, enforce same column, if Vy = 0, enforce same row
		int Vx = selectedTiles.get(1).getRow() - selectedTiles.get(0).getRow();
		int Vy = selectedTiles.get(1).getColumn() - selectedTiles.get(0).getColumn();

		boolean checkRowColumn = (Vx == 0 && selectedTiles.get(0).getRow() == tile.getRow())
				|| (Vy == 0 && selectedTiles.get(0).getColumn() == tile.getColumn());

		boolean adjacentToFirst = this.tile.isAdjacent(selectedTiles.get(0));
		boolean adjacentToSecond = this.tile.isAdjacent(selectedTiles.get(1));

		if ((adjacentToFirst || adjacentToSecond) && checkRowColumn
				&& tile.getBlockedSides() != TileSides.ALL_SIDES_BLOCKED) {
			commonGameAreaFrame.getSelectedTile3().setIcon(label.getIcon());
			selectedTiles.add(tile);
			this.tile.setActive(false);
			this.label.setVisible(false);
		} else {
			this.label.setIcon(grayIcon);
			throw new InvalidMoveException("You cannot pick up this tile!");
		}
	}
}
