package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;

import model.Player;
import model.commongamearea.BoardTile;
import model.shared.TileType;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.PersonalGameAreaFrame;
import view.personalgamearea.BookshelfTileLabel;

public class BookshelfTileController implements MouseListener {

	private BookshelfTileLabel label;
	private MainController mainController;
	private CommonGameAreaFrame commonGameAreaFrame;
	private PersonalGameAreaFrame personalGameAreaFrame;

	public BookshelfTileController(BookshelfTileLabel label, CommonGameAreaFrame commonGameAreaFrame,
			PersonalGameAreaFrame personalGameAreaFrame, MainController mainController) {

		this.label = label;
		this.mainController = mainController;
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.personalGameAreaFrame = personalGameAreaFrame;

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
}
