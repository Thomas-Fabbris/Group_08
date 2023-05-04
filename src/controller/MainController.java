package controller;

import java.util.ArrayList;
import java.util.function.Supplier;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.PersonalGameArea;
import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.commongamearea.CommonObjectiveCard;
import model.personalgamearea.PointTile;
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

		//Common game area initialisation
		assignBoardTiles();
		assignCommonObjectiveCards();
		assignPointTiles();
	}

	
	
	// ----------- Common game area operations -----------
	
	/**
	 * Updates each common objective card's point tile
	 */
	private void assignPointTiles() {		
		CommonObjectiveCard card1 = commonGameArea.getCard1();
		CommonObjectiveCard card2 = commonGameArea.getCard2();

		updatePointTileLabel(card1.getPointTiles().lastElement(), commonGameAreaFrame.getCard1PointTile());
		updatePointTileLabel(card2.getPointTiles().lastElement(), commonGameAreaFrame.getCard2PointTile());
	}

	/**
	 * Updates the amount of points shown on the tile
	 */
	private void updatePointTileLabel(PointTile tile, JLabel label) {
		int rotation = 352;
		updateTileOnScreen(label, () -> "Assets/Point_tiles/Xp.jpg".replace("X", Integer.toString(tile.getPoints())), rotation);
	}
	
	/**
	 * Updates the image of the label to reflect the TileType of the BoardTile
	 */
	private void updateBoardTileLabel(BoardTile tile, BoardTileLabel label) {
		updateTileOnScreen(label, () -> "Assets/tiles/" + tile.getType().toString() + ".png");
	}
	
	/**
	 * Updates the Label's icon; gets the path of the new image from the Supplier
	 */
	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getWidth(), tileLabel.getHeight(), pathToNewImage.get());
		tileLabel.setIcon(icon);
	}
	
	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage, boolean isGray) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getWidth(), tileLabel.getHeight(), pathToNewImage.get());
		
		if(isGray)
			icon = ImageUtils.getGrayImage(icon);
		
		tileLabel.setIcon(icon);
	}
	
	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage, int iconRotation) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getWidth(), tileLabel.getHeight(), pathToNewImage.get());
		
		if(iconRotation != 0 && iconRotation != 360)
			icon = ImageUtils.rotateIcon(icon, iconRotation);

		tileLabel.setIcon(icon);
	}

	/**
	 * Assigns a Label to each Tile on the board and save that Label in
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
					boardTileLabels[i][j] = new BoardTileLabel(i, j, boardLabel.getSize());
					updateBoardTileLabel(board.getTile(i, j), boardTileLabels[i][j]);
					boardLabel.add(boardTileLabels[i][j]);
				}
			}
		}
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
