package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;

import model.Player;
import model.commongamearea.BoardTile;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;

public class selectedTileController implements MouseListener {

	private BoardTile tile;
	private JLabel label;
	private Player currentPlayer;
	private int id; // Used to identify which selected tile the use is interacting with
	private CommonGameAreaFrame commonGameAreaFrame;
	private PersonalGameAreaFrame personalGameAreaFrame;
	private MainController mainController;

	public selectedTileController(int id, CommonGameAreaFrame commonGameAreaFrame,
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

		// TODO: if player ends turn before placing all tiles in the bookshelf,
		// discarded tiles return to the board
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

	public BoardTile getTile() {
		return tile;
	}

	public void setTile(BoardTile tile) {
		this.tile = tile;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
