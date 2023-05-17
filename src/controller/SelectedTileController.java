package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;

import model.Player;
import model.commongamearea.BoardTile;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.PersonalGameAreaFrame;

public class SelectedTileController implements MouseListener {

	private boolean selected = false;
	private int id; // Used to identify which selected tile the user is interacting with
	private CommonGameAreaFrame commonGameAreaFrame;
	private PersonalGameAreaFrame personalGameAreaFrame;
	private MainController mainController;

	public SelectedTileController(int id, CommonGameAreaFrame commonGameAreaFrame,
			PersonalGameAreaFrame personalGameAreaFrame, MainController mainController) {
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.personalGameAreaFrame = personalGameAreaFrame;
		this.mainController = mainController;

		if (id < 0 || id >= 3)
			throw new IllegalArgumentException("A selectedTileController must have an id < 0 and >= 3!");
		this.id = id;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		List<BoardTile> tiles = mainController.getCurrentPlayer().getSelectedTiles();
		System.out.println("selectedTileController.java: Selected tile " + tiles.get(this.id).getType());
		
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
		
		updateTileLabel(0);
		updateTileLabel(id);

	}
	
	//Given the id of the selectedTile, update its label to reflect the tile's type
	private void updateTileLabel(int id) {
		BoardTile tile = mainController.getCurrentPlayer().getSelectedTile(id);
		JLabel label = commonGameAreaFrame.getSelectedTile(id);
		String path = "Assets/tiles/" + tile.getType() + ".png";
		label.setIcon(ImageUtils.loadImageAsIcon(label.getSize(), path));
	}
}
