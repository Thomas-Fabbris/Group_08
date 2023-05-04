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
import model.personalgamearea.Bookshelf;
import model.personalgamearea.BookshelfTile;
import model.personalgamearea.PointTile;
import model.shared.Player;
import model.shared.TileType;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.PersonalGameAreaFrame;
import view.commongamearea.BoardTileLabel;
import view.personalgamearea.BookshelfLabel;
import view.personalgamearea.BookshelfTileLabel;

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

		//Personal game area initialisation
		assignBookshelfTiles();
		assignNextPlayerButtonController();
		assignPlayerNameTextController();
		assignPointsTextController();
		assignPersonalObjectiveCardLabel(4);
		
		//Common game area initialisation
		assignBoardTiles();
		assignCommonObjectiveCards();
		assignPointTiles();
	}

	// ----------- Personal game area operations -----------
	
	/**
	 * Assigns a Label to each Tile in the bookshelf and saves it in personalGameAreaFrame.bookshelfLabel.tiles[][]
	 */
	private void assignBookshelfTiles() {
		BookshelfLabel bookshelfLabel = personalGameAreaFrame.getBookshelfLabel();
				
		for (int row = 0; row < Bookshelf.ROWS; row++) {
			for (int column = 0; column < Bookshelf.COLUMNS; column++) {
				BookshelfTileLabel tile = new BookshelfTileLabel(row, column, bookshelfLabel.getSize());
				bookshelfLabel.tiles[row][column] = tile;
				bookshelfLabel.tilesContainer.add(tile);
			}
		}
	}
	
	private void assignPersonalObjectiveCardLabel(int cardId) {
		JLabel personalObjectiveCard = personalGameAreaFrame.getPersonalObjectiveCardLabel();
		String path = "Assets/Carte_Obiettivo_Personale/Carta_X.png".replace("X", Integer.toString(cardId));
		ImageIcon icon = ImageUtils.loadImageAsIcon(personalObjectiveCard.getSize(), path);
		personalObjectiveCard.setIcon(icon);
	}
	
	private void assignNextPlayerButtonController() {
		JLabel button = personalGameAreaFrame.getNextPlayerButton();
		//TODO: add missing implementation
	}
	
	private void assignPlayerNameTextController() {
		JLabel playerName = personalGameAreaFrame.getPlayerName();
		playerName.setText("Player's turn");
	}
	
	private void assignPointsTextController() {
		JLabel points = personalGameAreaFrame.getPoints();
		points.setText("Points: 25");
	}
	
	/**
	 * Updates the image of the label to reflect the TileType of the BookshelfTile
	 */
	private void updateBookshelfTileLabel(BookshelfTile tile, BookshelfTileLabel label) {
		updateTileOnScreen(label, () -> "Assets/tiles/" + tile.getType().toString() + ".png");
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
	 * Assigns a Label to each Tile on the board and saves it in commonGameAreaFrame.boardTileLabels
	 */
	private void assignBoardTiles() {

		int ROWS = commonGameArea.getBoard().getTiles()[0].length;
		int COLUMNS = commonGameArea.getBoard().getTiles().length;

		Board board = commonGameArea.getBoard();
		JLabel boardLabel = commonGameAreaFrame.getBoardLabel();
		BoardTileLabel[][] boardTileLabels = commonGameAreaFrame.getBoardTiles();

		boardTileLabels = new BoardTileLabel[ROWS][COLUMNS];

		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				if (board.getValidPositions()[row][column]) {
					boardTileLabels[row][column] = new BoardTileLabel(row, column, boardLabel.getSize());
					updateBoardTileLabel(board.getTile(row, column), boardTileLabels[row][column]);
					boardLabel.add(boardTileLabels[row][column]);
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

	/**
	 * Updates the Label's icon; gets the path of the new image from the Supplier
	 */
	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getWidth(), tileLabel.getHeight(), pathToNewImage.get());
		tileLabel.setIcon(icon);
	}
	
	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage, boolean isGrayscale) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getWidth(), tileLabel.getHeight(), pathToNewImage.get());
		
		if(isGrayscale)
			icon = ImageUtils.getGrayImage(icon);
		
		tileLabel.setIcon(icon);
	}
	
	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage, int iconRotation) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getWidth(), tileLabel.getHeight(), pathToNewImage.get());
		
		if(iconRotation != 0 && iconRotation != 360)
			icon = ImageUtils.rotateIcon(icon, iconRotation);
		
		tileLabel.setIcon(icon);
	}
}
