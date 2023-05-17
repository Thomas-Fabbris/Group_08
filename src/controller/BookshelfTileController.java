package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Player;
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
