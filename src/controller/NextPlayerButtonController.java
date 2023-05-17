package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.commongamearea.BoardTile;
import view.CommonGameAreaFrame;
import view.ImageUtils;

public class NextPlayerButtonController implements MouseListener {

	private JLabel button;
	private ImageIcon defaultStateIcon;
	private ImageIcon pressedStateIcon;
	private CommonGameArea commonGameArea;
	private CommonGameAreaFrame commonGameAreaFrame;
	private MainController mainController;

	public NextPlayerButtonController(JLabel button, CommonGameArea commonGameArea, CommonGameAreaFrame commonGameAreaFrame,
			MainController mainController) {
		this.button = button;
		this.defaultStateIcon = (ImageIcon) button.getIcon();
		this.pressedStateIcon = ImageUtils.loadImageAsIcon(defaultStateIcon.getIconWidth(),
				defaultStateIcon.getIconHeight(), "Assets/ArrowButton/ArrowButtonPressed.png");
		this.commonGameArea = commonGameArea;
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.mainController = mainController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// each time the player moves a tile to the bookshelf, the tile is removed from
		// the list,
		// tiles that are still in the list when the turn ends will return to the
		// board
		
		List<BoardTile> tiles = mainController.getCurrentPlayer().getSelectedTiles();
		clearSelectedTiles(tiles);
		commonGameArea.updateCurrentBlockedTiles();

		nextTurn();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		button.setIcon(pressedStateIcon);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button.setIcon(defaultStateIcon);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	// Advances the turn
	private void nextTurn() {
		int currentPlayerId = mainController.getCurrentPlayer().getId();
		int nextId = currentPlayerId + 1;

		if (nextId > mainController.getLastPlayer().getId())
			nextId = 0;

		mainController.setCurrentPlayer(mainController.getPlayer(nextId));
	}

	// Returns to the board the selected tile that have not been moved to the
	// bookshelf
	private void clearSelectedTiles(List<BoardTile> tiles) {
		for (BoardTile boardTile : tiles) {
			returnTileToBoard(boardTile);
		}

		tiles.clear();
		commonGameAreaFrame.getSelectedTile1().setIcon(null);
		commonGameAreaFrame.getSelectedTile2().setIcon(null);
		commonGameAreaFrame.getSelectedTile3().setIcon(null);
	}

	private void returnTileToBoard(BoardTile tile) {
		tile.setActive(true);
		int row = tile.getRow();
		int column = tile.getColumn();
		commonGameAreaFrame.getBoardTiles()[row][column].setVisible(true);
	}

}
