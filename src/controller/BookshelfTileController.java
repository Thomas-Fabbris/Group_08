package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import model.CommonGameArea;
import model.Player;
import model.commongamearea.BoardTile;
import model.commongamearea.CommonObjectiveCard;
import model.shared.TileType;
import view.CommonGameAreaFrame;
import view.personalgamearea.BookshelfTileLabel;

public class BookshelfTileController implements MouseListener {

	private BookshelfTileLabel label;
	private CommonGameArea commonGameArea;
	private CommonGameAreaFrame commonGameAreaFrame;
	private MainController mainController;

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

	@Override
	public void mouseClicked(MouseEvent e) {
		Player currentPlayer = mainController.getCurrentPlayer();
		List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();

		// Gets the coordinates of the label, which correspond to the coordinates on the
		// bookshelf
		int row = label.getRow();
		int column = label.getColumn();

		if (selectedTiles.size() > 0 && currentPlayer.bookshelf.getTile(row, column).getType() == TileType.NULL) {

			// If this is the first tile added to the bookshelf on this turn, do not enforce
			// a column
			if (!currentPlayer.hasSelectedColumn()) {
				moveFirstSelectedTileToBookshelf(column);
				currentPlayer.setSelectedColumn(column);
			} else if (currentPlayer.getSelectedColumn() == column) {
				moveSelectedTileToBookshelf();
			}
		}

		// When the player has inserted all the selected tiles in the bookshelf, check if a
		// goal is satisfied
		if (selectedTiles.size() == 0) {
			try {
				checkCommonObjectives(currentPlayer);
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

		mainController.updateBookshelfLabel();
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

	// Moves the first selected tile to the bookshelf and then removes it
	private void moveFirstSelectedTileToBookshelf(int column) {

		Player currentPlayer = mainController.getCurrentPlayer();
		int row = currentPlayer.bookshelf.getFirstFreeRow(column);

		List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();
		currentPlayer.getBookshelf().setTileType(selectedTiles.get(0).getType(), row, column);

		currentPlayer.getSelectedTiles().remove(0);
		mainController.updateSelectedTileLabels();
	}

	private void moveSelectedTileToBookshelf() {

		Player currentPlayer = mainController.getCurrentPlayer();
		int column = currentPlayer.getSelectedColumn();
		int row = currentPlayer.bookshelf.getFirstFreeRow(column);

		List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();
		currentPlayer.getBookshelf().setTileType(selectedTiles.get(0).getType(), row, column);

		currentPlayer.getSelectedTiles().remove(0);
		mainController.updateSelectedTileLabels();
	}

	private void checkCommonObjectives(Player currentPlayer) {
		CommonObjectiveCard card1 = commonGameArea.getCard1();
		CommonObjectiveCard card2 = commonGameArea.getCard2();

		boolean card1Satisfied = false;
		boolean card2Satisfied = false;

		// If the player has already been awarded a point tile from this card, we don't
		// have to check again the card's goal condition
		if (!currentPlayer.hasCompletedCommonGoal1()) {
			card1Satisfied = card1.getRelatedCommonGoal().checkCommonGoal(currentPlayer.bookshelf);
		}

		if (!currentPlayer.hasCompletedCommonGoal2()) {
			card2Satisfied = card2.getRelatedCommonGoal().checkCommonGoal(currentPlayer.bookshelf);
		}

		if (card1Satisfied) {
			System.out.println("BookshelfTileController.java " + currentPlayer.getName() + " has completed goal 1!");
			card1.award(currentPlayer);
			mainController.updatePointsText(currentPlayer);
			mainController.updatePlayerPointTile1Label(currentPlayer);
			mainController.updateBoardPointTileLabel(card1.getPointTiles().lastElement(),
					commonGameAreaFrame.getCard1PointTile());
		}

		if (card2Satisfied) {
			System.out.println("BookshelfTileController.java " + currentPlayer.getName() + " has completed goal 2!");
			card2.award(currentPlayer);
			mainController.updatePointsText(currentPlayer);
			mainController.updatePlayerPointTile2Label(currentPlayer);
			mainController.updateBoardPointTileLabel(card2.getPointTiles().lastElement(),
					commonGameAreaFrame.getCard2PointTile());
		}
	}
}
