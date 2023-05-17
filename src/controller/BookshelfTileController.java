package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import model.Player;
import model.commongamearea.BoardTile;
import view.PersonalGameAreaFrame;
import view.personalgamearea.BookshelfTileLabel;

public class BookshelfTileController implements MouseListener {

	private BookshelfTileLabel label;
	private MainController mainController;
	private PersonalGameAreaFrame personalGameAreaFrame;

	public BookshelfTileController(BookshelfTileLabel label, PersonalGameAreaFrame personalGameAreaFrame,
			MainController mainController) {

		this.label = label;
		this.mainController = mainController;
		this.personalGameAreaFrame = personalGameAreaFrame;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Player currentPlayer = mainController.getCurrentPlayer();
		List<BoardTile> selectedTiles = currentPlayer.getSelectedTiles();
		
		// Prendo le coordinate del label, queste corrispondono alle coordinate nella bookshelf
		int row = label.getRow();
		int column = label.getColumn();
		
		System.out.println("BookshelfTileController.java: row " +row + " col " + column);
		System.out.println(currentPlayer.getBookshelf().getTile(row, column).getType());
		
		currentPlayer.getBookshelf().setTileType(selectedTiles.get(0).getType(), row, column);
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

}
