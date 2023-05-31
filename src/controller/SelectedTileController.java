package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import model.commongamearea.BoardTile;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;

public class SelectedTileController implements MouseListener {

	private int id; 
	private MainController mainController;

	/**
	 * This is the constructor of the class
	 * 
	 * @param id, identifying the tile the user is interacting with
	 * @param commonGameAreaFrame, displayed in the left-side of the GUI
	 * @param personalGameAreaFrame, displayed in the right-side of the GUI
	 * @param mainController, the main controller of the program
	 */
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
	
	/**
	 * This method brings the clicked tile to the top, being the first to be placed in the bookshelf
	 * 
	 * @param e, representing the event which triggered this method
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		List<BoardTile> tiles = mainController.getCurrentPlayer().getSelectedTiles();		
		swapTiles(tiles);
	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 * @param e (MouseEvent)
	 **/
	@Override
	public void mousePressed(MouseEvent e) {

	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 * @param e (MouseEvent)
	 **/
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 * @param e (MouseEvent)
	 **/
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 * @param e (MouseEvent)
	 **/
	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	/**
	 * This method swaps the clicked tile with the first tile, setting it to be the first to go
	 * in the bookshelf
	 * 
	 * @param selectedTiles
	 */
	private void swapTiles(List<BoardTile> selectedTiles) {
		BoardTile tmp = selectedTiles.get(0);
		selectedTiles.set(0, selectedTiles.get(id));
		selectedTiles.set(id, tmp);
		
		mainController.updateSelectedTileLabel(0);
		mainController.updateSelectedTileLabel(id);
	}
}
