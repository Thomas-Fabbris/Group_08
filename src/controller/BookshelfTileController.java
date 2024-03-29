package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import model.CommonGameArea;
import model.Player;
import model.commongamearea.BoardTile;
import model.commongamearea.CommonObjectiveCard;
import model.personalgamearea.IllegalActionException;
import model.shared.TileType;
import view.CommonGameAreaFrame;
import view.personalgamearea.BookshelfTileLabel;

public class BookshelfTileController implements MouseListener {

	private BookshelfTileLabel label;
	private CommonGameArea commonGameArea;
	private CommonGameAreaFrame commonGameAreaFrame;
	private MainController mainController;
/**
 * The constructor defines a new instance of the class {@code BookshelfTileController}
 * @param label, the label
 * @param commonGameArea, part of the program's model
 * @param commonGameAreaFrame, displayed in the left side of the GUI
 * @param mainController of the entire game
 */
	public BookshelfTileController(BookshelfTileLabel label, CommonGameArea commonGameArea,
			CommonGameAreaFrame commonGameAreaFrame, MainController mainController) {
		if (commonGameArea == null) {
			throw new NullPointerException(
					"commonGameArea cannot be set to null while creating a BookShelfTileController instance!");
		}
		if (commonGameAreaFrame == null) {
			throw new NullPointerException(
					"commonGameAreaFrame cannot be set to null while creating a BookShelfTileController instance!");
		}
		if (label == null) {
			throw new NullPointerException(
					"label cannot be set to null while creating a BookShelfTileController instance!");
		}
		if (mainController == null) {
			throw new NullPointerException(
					"mainController cannot be set to null while creating a BookShelfTileController instance!");
		}
		this.commonGameArea = commonGameArea;
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.label = label;
		this.mainController = mainController;

	}
	/**
	 * This method is responsible for controlling the flow of the game when a player insert some tiles in his bookshelf
	 * @param e, representing the event which triggered this method
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Player currentPlayer = mainController.getCurrentPlayer();
		List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();

		// Gets the coordinates of the label, which correspond to the coordinates on the
		// bookshelf
		int row = label.getRow();
		int column = label.getColumn();

		try {

			// Check for the case in which the player tries to insert more tiles in a column
			// than there are free cells
			if (!currentPlayer.getBookshelf().columnHasSpaceAvailable(column, selectedTiles.size())) {
				currentPlayer.resetSelectedColumn();
				throw new IllegalActionException("Not enough space in this column!");
			}

			if (selectedTiles.size() > 0) {
				// If this is the first tile added to the bookshelf on this turn, do not enforce
				// a column
				if (!currentPlayer.hasSelectedColumn()) {
					moveFirstSelectedTileToBookshelf(column);
					currentPlayer.setSelectedColumn(column);
				} else if (currentPlayer.getSelectedColumn() == column) {
					moveSelectedTileToBookshelf();
				}
			}

			// When the player has inserted all the selected tiles in the bookshelf, check
			// if a goal is satisfied
			if (selectedTiles.size() == 0) {
				try {
					checkCommonObjectives(currentPlayer);
					checkPersonalObjective(currentPlayer);
				} catch (IllegalArgumentException e2) {
					e2.printStackTrace();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

			// Check to decide if the current player should get the GameEndTile
			if (!commonGameArea.getGameEndTile().hasBeenAwarded() && currentPlayer.bookshelf.isFull()) {
				commonGameArea.getGameEndTile().award(currentPlayer);
				mainController.updatePointsText(currentPlayer);
				mainController.updatePlayerGameEndTileLabel(currentPlayer);
				commonGameAreaFrame.getEndOfGameTile().setVisible(false);
				mainController.setGameState(GameState.LAST_TURN);
			}

		} catch (IllegalActionException e2) {
			this.mainController.getPersonalGameAreaFrame().getWarnings().setText(e2.getMessage());
			this.mainController.getPersonalGameAreaFrame().getWarnings().setVisible(true);
		}

		mainController.updateBookshelfLabel();
	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 *  @param e (MouseEvent)
	 **/
	@Override
	public void mousePressed(MouseEvent e) {

	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 *  @param e (MouseEvent)
	 **/
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 *  @param e (MouseEvent)
	 **/
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 *  @param e (MouseEvent)
	 **/
	@Override
	public void mouseExited(MouseEvent e) {

	}
	/**
	 * The method {@code moveFirstSelectedTileToBookshelf} moves the first selected tile to the bookshelf and then removes it
	 * @param column, the colum in which we want to add the selected tiles
	 */
	private void moveFirstSelectedTileToBookshelf(int column) {

		Player currentPlayer = mainController.getCurrentPlayer();
		int row = currentPlayer.bookshelf.getFirstFreeRow(column);

		List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();
		currentPlayer.getBookshelf().setTileType(selectedTiles.get(0).getType(), row, column);

		currentPlayer.getSelectedTiles().remove(0);
		currentPlayer.getBookshelf().setStateChanged(true);
		mainController.updateSelectedTileLabels();
	}

	private void moveSelectedTileToBookshelf() {

		Player currentPlayer = mainController.getCurrentPlayer();
		int column = currentPlayer.getSelectedColumn();
		int row = currentPlayer.bookshelf.getFirstFreeRow(column);

		List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();
		currentPlayer.getBookshelf().setTileType(selectedTiles.get(0).getType(), row, column);

		currentPlayer.getSelectedTiles().remove(0);
		currentPlayer.getBookshelf().setStateChanged(true);
		mainController.updateSelectedTileLabels();
	}

	private void checkCommonObjectives(Player player) {
		if (player == null) {
			throw new NullPointerException(
					"player cannot be set to null when calling BookShelfTileController.checkCommonObjectives() method!");
		}
		CommonObjectiveCard[] cards = commonGameArea.getCommonObjectiveCards();

		// The first condition checks whether the player already has a point tile from
		// this card, the second condition checks if if card's goal is satisfied

		for (int i = 0; i < cards.length; i++) {
			if (!player.hasCompletedCommonGoal(i)
					&& cards[i].getRelatedCommonGoal().checkCommonGoal(player.bookshelf)) {

				cards[i].award(player);
				mainController.updatePointsText(player);
				mainController.updatePlayerPointTileLabel(player, i);
				mainController.updateBoardPointTileLabel(cards[i].getLastPointTile(),
						commonGameAreaFrame.getPointTile(i));
			}
		}
	}

	private void checkPersonalObjective(Player player) {
		if (player == null) {
			throw new NullPointerException(
					"player cannot be set to null when calling BookShelfTileController.checkPersonalObjective() method!");
		}
		int matches = player.getObjectiveCard().countSatisfiedGoals(player.getBookshelf());

		// We check how many tiles in the bookshelf match the personal objective card

		// If the player has 0 matches, award the first match; if the player has 1
		// match, award the second match and so on

		switch (matches) {
		case 1:
			if (player.getPreviousObjectiveCardMatches() < matches) {
				player.addPoints(1);
				player.setPreviousObjectiveCardMatches(matches);
			}
			break;

		case 2:
			if (player.getPreviousObjectiveCardMatches() < matches) {
				player.addPoints(1);
				player.setPreviousObjectiveCardMatches(matches);
			}
			break;

		case 3:
			if (player.getPreviousObjectiveCardMatches() < matches) {
				player.addPoints(2);
				player.setPreviousObjectiveCardMatches(matches);
			}
			break;

		case 4:
			if (player.getPreviousObjectiveCardMatches() < matches) {
				player.addPoints(2);
				player.setPreviousObjectiveCardMatches(matches);
			}
			break;

		case 5:
			if (player.getPreviousObjectiveCardMatches() < matches) {
				player.addPoints(3);
				player.setPreviousObjectiveCardMatches(matches);
			}
			break;

		case 6:
			if (player.getPreviousObjectiveCardMatches() < matches) {
				player.addPoints(3);
				player.setPreviousObjectiveCardMatches(matches);
			}
			break;

		default:
			return;
		}

		mainController.updatePointsText(player);
	}
}
