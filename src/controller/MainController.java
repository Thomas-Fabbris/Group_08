package controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.PersonalGameArea;
import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.commongamearea.CommonObjectiveCard;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.PersonalGameAreaFrame;
import view.commongamearea.BoardTileLabel;

public class MainController {

	ArrayList<String> playerNames;

	CommonGameArea commonGameArea;
	PersonalGameArea personalGameArea;

	CommonGameAreaFrame commonGameAreaFrame;
	PersonalGameAreaFrame personalGameAreaFrame;

	public MainController(PersonalGameAreaFrame personalGameAreaFrame, CommonGameAreaFrame commonGameAreaFrame,
			ArrayList<String> playerNames, PersonalGameArea personalGameArea, CommonGameArea commonGameArea) {
		this.playerNames = playerNames;

		this.commonGameArea = commonGameArea;
		this.personalGameArea = personalGameArea;

		this.commonGameAreaFrame = commonGameAreaFrame;
		this.personalGameAreaFrame = personalGameAreaFrame;

		assignBoardTiles();
		assignCommonObjectiveCards();
	}

	/**
	 * Assign a Label to each Tile on the board and save that Label in
	 * commonGameAreaFrame.boardTileLabels
	 */
	private void assignBoardTiles() {

		int ROWS = commonGameArea.getBoard().getTiles()[0].length;
		int COLUMNS = commonGameArea.getBoard().getTiles().length;

		Board board = commonGameArea.getBoard();
		JLabel boardLabel = commonGameAreaFrame.getBoardLabel();
		BoardTileLabel[][] boardTileLabels = commonGameAreaFrame.getBoardTiles();

		boardTileLabels = new BoardTileLabel[ROWS][COLUMNS];

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (board.getValidPositions()[i][j]) {
					boardTileLabels[i][j] = new BoardTileLabel(board.getTileType(i, j), i, j, boardLabel.getSize());
					boardLabel.add(boardTileLabels[i][j]);
				}
			}
		}
	}

	public void updateBoardTileType(BoardTile tile, BoardTileLabel label) {
		String path = "Assets/tiles/" + tile.getType().toString() + ".png";
		ImageIcon icon = ImageUtils.loadImageAsIcon(label.getWidth(), label.getHeight(), path);
		label.setIcon(icon);
	}

	/**
	 * Assign a Label to each common objective card based on the card's id
	 */
	private void assignCommonObjectiveCards() {

		CommonObjectiveCard card1 = commonGameArea.getCard1();
		CommonObjectiveCard card2 = commonGameArea.getCard2();

		JLabel card1Label = commonGameAreaFrame.getCard1Label();
		JLabel card2Label = commonGameAreaFrame.getCard2Label();

		String path = "Assets/Carte_Obiettivo_Comune/Carta_X.png".replace("X", Integer.toString(card1.getId()));
		ImageIcon icon = ImageUtils.loadImageAsIcon(card1Label.getWidth(), card1Label.getHeight(), path);
		card1Label.setIcon(icon);

		path = "Assets/Carte_Obiettivo_Comune/Carta_X.png".replace("X", Integer.toString(card2.getId()));
		icon = ImageUtils.loadImageAsIcon(card1Label.getWidth(), card1Label.getHeight(), path);
		card2Label.setIcon(icon);
	}

}
