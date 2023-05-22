package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.commongamearea.BoardTile;
import model.personalgamearea.IllegalActionException;
import observer.Observable;
import observer.Observer;
import view.CommonGameAreaFrame;
import view.ImageUtils;

public class NextPlayerButtonController implements MouseListener, Observable {

	private JLabel button;
	private ImageIcon defaultStateIcon;
	private ImageIcon pressedStateIcon;
	private CommonGameArea commonGameArea;
	private CommonGameAreaFrame commonGameAreaFrame;
	private MainController mainController;

	private List<Observer> boardTileControllers;

	public NextPlayerButtonController(JLabel button, CommonGameArea commonGameArea,
			CommonGameAreaFrame commonGameAreaFrame, MainController mainController) {
		if(mainController == null) {
			throw new NullPointerException("mainController cannot be set to null when creating a NextPlayerButtonController instance!");
		}
		if(commonGameArea == null) {
			throw new NullPointerException("commonGameArea cannot be set to null when creating a NextPlayerButtonController instance!");
		}
		if(commonGameAreaFrame == null) {
			throw new NullPointerException("commonGameAreaFrame cannot be set to null when creating a NextPlayerButtonController instance!");
		}
		if(button == null) {
			throw new NullPointerException("button cannot be set to null when creating a NextPlayerButtonController instance!");
		}
		this.button = button;
		this.defaultStateIcon = (ImageIcon) button.getIcon();
		this.pressedStateIcon = ImageUtils.loadImageAsIcon(defaultStateIcon.getIconWidth(),
				defaultStateIcon.getIconHeight(), "Assets/ArrowButton/ArrowButtonPressed.png");
		this.commonGameArea = commonGameArea;
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.mainController = mainController;

		this.boardTileControllers = new ArrayList<>();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// each time the player moves a tile to the bookshelf, the tile is removed from
		// the list,
		// tiles that are still in the list when the turn ends will return to the
		// board
		try {
			this.mainController.getPersonalGameAreaFrame().getWarnings().setVisible(false);
			List<BoardTile> selectedTiles = mainController.getCurrentPlayer().getSelectedTiles();

			// Check for the case in which the player picks up three tiles and tries to
			// return the middle one to the board
			if (selectedTiles.size() == 2 && !selectedTiles.get(0).isAdjacent(selectedTiles.get(1))) {
				throw new IllegalActionException("Please place all tiles in the bookshelf before ending your turn!");
			}

			clearSelectedTiles(selectedTiles);
			commonGameArea.updateCurrentBlockedTiles();
			nextTurn();

		} catch (IllegalActionException ex) {
			this.mainController.getPersonalGameAreaFrame().getWarnings().setText(ex.getMessage());
			this.mainController.getPersonalGameAreaFrame().getWarnings().setVisible(true);
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

	@Override
	public void addObserver(Observer o) {
		boardTileControllers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		if (boardTileControllers.contains(o)) {
			boardTileControllers.remove(o);
		}
	}

	@Override
	public void notify(Object[] data) {
		for (int i = 0; i < boardTileControllers.size(); i++) {
			boardTileControllers.get(i).update(data);
		}
	}

	// Advances the turn
	private void nextTurn() throws IllegalActionException {
		if(!this.mainController.getCurrentPlayer().getBookshelf().isStateChanged()) {
			throw new IllegalActionException("You have to make your move before you can proceed to the next turn!"); 
		 }
	
		this.mainController.getCurrentPlayer().resetSelectedColumn();

		int currentPlayerId = mainController.getCurrentPlayer().getId();
		int nextId = currentPlayerId + 1;

		if (nextId > mainController.getLastPlayer().getId())
			nextId = 0;

		this.mainController.setCurrentPlayer(mainController.getPlayer(nextId));
		this.mainController.getCurrentPlayer().getBookshelf().setStateChanged(false);
		this.mainController.getGameToken().setCurrentOwner(this.mainController.getCurrentPlayer());

		// Check to decide if the game ends when the nextPlayerButton is pressed
		if (mainController.getGameState() == GameState.LAST_TURN
				&& currentPlayerId == mainController.getLastPlayer().getId()) {
			System.out.println("[" + this.getClass().getSimpleName() + "] Game ended!");
			mainController.setGameState(GameState.ENDED);
			mainController.displayGameEndScreen();
		}

		// Check if the board needs to be refilled
		if (commonGameArea.getBoard().isFull()) {
			System.out.println("[" + this.getClass().getSimpleName() + "] Refilling board");
			commonGameArea.getBoard().refill();
			commonGameArea.updateCurrentBlockedTiles();
			mainController.updateAllBoardTileLabels();
			notify(new Object[] { commonGameArea.getBoard().getBoardTiles(),
					commonGameAreaFrame.getBoardTilesLabels() });
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
		commonGameAreaFrame.getBoardTilesLabels()[row][column].setVisible(true);
	}
}
