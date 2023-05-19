package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.commongamearea.BoardTile;
import model.personalgamearea.IllegalActionException;
import view.CommonGameAreaFrame;
import view.ImageUtils;

public class NextPlayerButtonController implements MouseListener {

	private JLabel button;
	private ImageIcon defaultStateIcon;
	private ImageIcon pressedStateIcon;
	private CommonGameArea commonGameArea;
	private CommonGameAreaFrame commonGameAreaFrame;
	private MainController mainController;

	public NextPlayerButtonController(JLabel button, CommonGameArea commonGameArea,
			CommonGameAreaFrame commonGameAreaFrame, MainController mainController) {
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
		try {
			List<BoardTile> tiles = mainController.getCurrentPlayer().getSelectedTiles();
			clearSelectedTiles(tiles);
			commonGameArea.updateCurrentBlockedTiles();

			nextTurn();
			fillInBoard();
		} catch (IllegalActionException ex) {
			this.mainController.getPersonalGameAreaFrame().getWarnings().setText(ex.getMessage());
			this.mainController.getPersonalGameAreaFrame().getWarnings().setVisible(true);
		}
	}

	private void fillInBoard() {
		if (this.commonGameArea.getBoard().refillCheck()) {
			this.commonGameArea.getBoard().refill();
			this.mainController.assignBoardTiles();
		}

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
	private void nextTurn() throws IllegalActionException {
		/*
		 * TODO Delete comment when BookShelf is completely implementated if
		 * (!this.mainController.getCurrentPlayer().getBookshelf().isStateChanged()) {
		 * throw new
		 * IllegalActionException("Warning, you have to make your move before you can pass your turn!"
		 * ); }
		 */
		this.mainController.getCurrentPlayer().resetSelectedColumn();

		int currentPlayerId = mainController.getCurrentPlayer().getId();
		int nextId = currentPlayerId + 1;

		if (nextId > mainController.getLastPlayer().getId())
			nextId = 0;

		this.mainController.setCurrentPlayer(mainController.getPlayer(nextId));
		this.mainController.getCurrentPlayer().getBookshelf().setStateChanged(false);
		this.mainController.getGameToken().setCurrentOwner(this.mainController.getCurrentPlayer());

		// Check to decide if the game ends when the nextPlayerButton is pressed
		if (mainController.getGameState() == GameState.LAST_TURN && currentPlayerId == mainController.getLastPlayer().getId()) {
			System.out.println("NextPlayerButtonController.java Game ended!");
			mainController.setGameState(GameState.ENDED);
			mainController.displayGameEndScreen();
		}
	}

	// Returns to the board selected tiles that have not been moved to the
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
