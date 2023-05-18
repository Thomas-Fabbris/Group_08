package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import model.commongamearea.BoardTile;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;

public class SelectedTileController implements MouseListener {

	private int id; // Used to identify which selected tile the user is interacting with
	private MainController mainController;

	public SelectedTileController(int id, CommonGameAreaFrame commonGameAreaFrame,
			PersonalGameAreaFrame personalGameAreaFrame, MainController mainController) {
		if(mainController == null) {
			throw new NullPointerException("mainController must not be set to null while creating a SelectedTileController instance!");
		}
		this.mainController = mainController;

		if (id < 0 || id >= 3)
			throw new IllegalArgumentException("A SelectedTileController must have an id < 0 and >= 3!");
		this.id = id;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		List<BoardTile> tiles = mainController.getCurrentPlayer().getSelectedTiles();		
		swapTiles(tiles);
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
	
	/**
	 * Swaps the clicked tile with the first tile
	 */
	private void swapTiles(List<BoardTile> selectedTiles) {
		BoardTile tmp = selectedTiles.get(0);
		selectedTiles.set(0, selectedTiles.get(id));
		selectedTiles.set(id, tmp);
		
		mainController.updateSelectedTileLabel(0);
		mainController.updateSelectedTileLabel(id);
	}
}
