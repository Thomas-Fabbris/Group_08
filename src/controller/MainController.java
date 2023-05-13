package controller;

import java.util.ArrayList;
import java.util.function.Supplier;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.Player;
import model.GameToken;
import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.commongamearea.CommonObjectiveCard;
import model.commongamearea.GameEndTile;
import model.commongamearea.PointTile;
import model.personalgamearea.Bookshelf;
import model.personalgamearea.BookshelfTile;
import model.personalgamearea.PersonalObjectiveCard;
import model.shared.IdGenerator;
import model.shared.TileType;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.PersonalGameAreaFrame;
import view.commongamearea.BoardTileLabel;
import view.personalgamearea.BookshelfLabel;
import view.personalgamearea.BookshelfTileLabel;

public class MainController {

	public static GameState gameState;

	private Player[] players;
	private Player currentPlayer;
	private Player lastPlayer;

	private CommonGameArea commonGameArea;

	private CommonGameAreaFrame commonGameAreaFrame;
	private PersonalGameAreaFrame personalGameAreaFrame;

	private GameToken gameToken;
	private GameEndTile gameEndTile = null;

	public MainController(PersonalGameAreaFrame personalGameAreaFrame, CommonGameAreaFrame commonGameAreaFrame,
			ArrayList<String> playerNames, CommonGameArea commonGameArea) {

		MainController.gameState = GameState.RUNNING;

		this.commonGameArea = commonGameArea;

		this.commonGameAreaFrame = commonGameAreaFrame;
		this.personalGameAreaFrame = personalGameAreaFrame;
		
		IdGenerator idGenerator = new IdGenerator();

		// Initialise players
		this.players = new Player[playerNames.size()];
		createPlayers(playerNames, idGenerator);
		this.currentPlayer = players[0];
		
		//Initialise gameToken 
		this.gameToken = new GameToken(this.currentPlayer);

		// Personal game area initialisation
		assignBookshelfTiles();
		assignNextPlayerButtonController();

		// Common game area initialisation
		assignBoardTiles();
		assignCommonObjectiveCards();
		assignPointTiles();

		startGame();
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	private void startGame() {

		players[0].setHasChair(true);
		players[0].bookshelf.fillRandom();
		setCurrentPlayer(players[0]);

		System.out.println("Matches: " + players[0].getObjectiveCard().countSatisfiedGoals(players[0].bookshelf));

//		while (MainController.gameState == GameState.RUNNING) {
//			int index = 0;
//
//			setCurrentPlayer(players[index++]);
//
//			if (index > 4) index = 0;
//			
//			// stop execution until nextPlayerButton is pressed
//		}
	}

	/**
	 * Create players from the names entered in the main menu
	 */
	private void createPlayers(ArrayList<String> names, IdGenerator idGenerator) {
		for (int i = 0; i < names.size(); i++) {
			this.players[i] = new Player(names.get(i), idGenerator);
			System.out.println("Created player with name '" + players[i].getName() + "' and id " + players[i].id);
		}
	}

	// ----------- Personal game area operations -----------

	/**
	 * Updates the info on the personal game area to reflect the current player's
	 * info
	 * 
	 * @param player
	 */
	private void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
		updatePlayerNameText(player);
		updatePointsText(player);
		updateBookshelfLabel(player.bookshelf);
		updatePointTile1Label(player);
		updatePointTile2Label(player);
		updatePersonalObjectiveCardLabel(player.getObjectiveCard());
		personalGameAreaFrame.getEndOfGameTile().setVisible(player.hasEndOfGameToken());
		personalGameAreaFrame.getChair().setVisible(player.hasChair());

		personalGameAreaFrame.getPointTile2();
		personalGameAreaFrame.getEndOfGameTile();
		personalGameAreaFrame.getChair();
	}

	/**
	 * updates the point tile to reflect the player's point tile
	 */
	private void updatePointTile1Label(Player player) {
		JLabel tileLabel = personalGameAreaFrame.getPointTile1();

		if (player.getPointTile(1) == null) {
			tileLabel.setVisible(false);
			return;
		}

		updatePlayerPointTileLabel(player.getPointTile(1), tileLabel);
	}

	/**
	 * updates the point tile to reflect the player's point tile
	 */
	private void updatePointTile2Label(Player player) {
		JLabel tileLabel = personalGameAreaFrame.getPointTile2();

		if (player.getPointTile(2) == null) {
			tileLabel.setVisible(false);
			return;
		}

		updatePlayerPointTileLabel(player.getPointTile(2), tileLabel);
	}

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

	/**
	 * Updates the type of each tile displayed on the GUI
	 */
	private void updateBookshelfLabel(Bookshelf bookshelf) {
		BookshelfLabel bookshelfLabel = personalGameAreaFrame.getBookshelfLabel();

		for (int row = 0; row < Bookshelf.ROWS; row++) {
			for (int column = 0; column < Bookshelf.COLUMNS; column++) {
				updateBookshelfTileLabel(bookshelf.getTile(row, column), bookshelfLabel.tiles[row][column]);
			}
		}
	}

	private void updatePersonalObjectiveCardLabel(PersonalObjectiveCard card) {
		JLabel personalObjectiveCard = personalGameAreaFrame.getPersonalObjectiveCardLabel();
		String path = "Assets/Carte_Obiettivo_Personale/Carta_X.png".replace("X", Integer.toString(card.cardId));
		ImageIcon icon = ImageUtils.loadImageAsIcon(personalObjectiveCard.getSize(), path);
		personalObjectiveCard.setIcon(icon);
	}

	private void assignNextPlayerButtonController() {
		JLabel button = personalGameAreaFrame.getNextPlayerButton();
		button.addMouseListener(new nextPlayerButtonController(button));
	}

	private void updatePlayerNameText(Player player) {
		JLabel playerName = personalGameAreaFrame.getPlayerName();
		playerName.setText(player.getName() + "'s turn");
	}

	private void updatePointsText(Player player) {
		JLabel points = personalGameAreaFrame.getPoints();
		points.setText("Points: " + player.getPoints());
	}

	/**
	 * Updates the image of the label to reflect the TileType of the BookshelfTile
	 */
	private void updateBookshelfTileLabel(BookshelfTile tile, BookshelfTileLabel label) {
		updateTileOnScreen(label, () -> "Assets/tiles/" + tile.getType().toString() + ".png");
	}

	/**
	 * Updates the image of the label to reflect the points of the PointTile
	 */
	private void updatePlayerPointTileLabel(PointTile tile, JLabel label) {
		updateTileOnScreen(label, () -> "Assets/Point_tiles/" + tile.getPoints() + ".png");
	}

	// ----------- Common game area operations -----------

	/**
	 * Updates each common objective card's point tile
	 */
	private void assignPointTiles() {
		CommonObjectiveCard card1 = commonGameArea.getCard1();
		CommonObjectiveCard card2 = commonGameArea.getCard2();

		updateBoardPointTileLabel(card1.getPointTiles().lastElement(), commonGameAreaFrame.getCard1PointTile());
		updateBoardPointTileLabel(card2.getPointTiles().lastElement(), commonGameAreaFrame.getCard2PointTile());
	}

	/**
	 * Updates the amount of points shown on the tile
	 */
	private void updateBoardPointTileLabel(PointTile tile, JLabel label) {
		int rotation = 352;
		updateTileOnScreen(label, () -> "Assets/Point_tiles/Xp.jpg".replace("X", Integer.toString(tile.getPoints())),
				rotation);
	}

	/**
	 * Updates the image of the label to reflect the TileType of the BoardTile
	 */
	private void updateBoardTileLabel(BoardTile tile, BoardTileLabel label) {
		updateTileOnScreen(label, () -> "Assets/tiles/" + tile.getType().toString() + ".png");
	}

	/**
	 * Assigns a Label to each Tile on the board and saves it in
	 * commonGameAreaFrame.boardTileLabels
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
					BoardTileLabel label = new BoardTileLabel(row, column, boardLabel.getSize());
					label.addMouseListener(
							new BoardTileController(board, board.getTile(row, column), label, board.getTiles(), commonGameAreaFrame));

					updateBoardTileLabel(board.getTile(row, column), label);
					boardLabel.add(label);
					boardTileLabels[row][column] = label;
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

		if (isGrayscale)
			icon = ImageUtils.getGrayImage(icon);

		tileLabel.setIcon(icon);
	}

	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage, int iconRotation) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getWidth(), tileLabel.getHeight(), pathToNewImage.get());

		if (iconRotation != 0 && iconRotation != 360)
			icon = ImageUtils.rotateIcon(icon, iconRotation);

		tileLabel.setIcon(icon);
	}
	
	// ----------- General-purpose game operations -----------
	
	/**
	 * This method checks if the game has finished and if so sets all the necessary parameters of the game
	 */
	public void checkEndOfGame() {
		if(this.currentPlayer.getBookshelf().isFull()) {
			this.currentPlayer.setEndOfGameToken(true);
			MainController.gameState = GameState.ENDED;
			this.setLastPlayer(determineLastPlayer());
			this.gameEndTile = new GameEndTile(this.commonGameArea.getBoard());
			this.gameEndTile.award(this.currentPlayer);
			saveCurrentPlayerInfo();
		}
	}
	
	/**
	 * This method allows to skip to the next turn of the game
	 */
	
	public void nextTurn() {
		checkCommonGoals();
		checkEndOfGame();
		saveCurrentPlayerInfo();
		Player nextPlayer = determineNextPlayer();
		if(!nextPlayer.equals(this.lastPlayer)) {
			this.gameToken.setCurrentOwner(currentPlayer);
			setCurrentPlayer(nextPlayer);
		}
	}
	
	public void checkCommonGoals() {
		if(!this.currentPlayer.hasCompletedCommonGoal1() && this.commonGameArea.getCard1().getRelatedCommonGoal().checkCommonGoal(this.currentPlayer.getBookshelf())){
			this.currentPlayer.setHasCompletedCommonGoal1(true);
			this.commonGameArea.getCard1().award(this.currentPlayer);
		}
		if(!this.currentPlayer.hasCompletedCommonGoal2() && this.commonGameArea.getCard2().getRelatedCommonGoal().checkCommonGoal(this.currentPlayer.getBookshelf())){
			this.currentPlayer.setHasCompletedCommonGoal2(true);
			this.commonGameArea.getCard2().award(this.currentPlayer);
		}
	}
	
	public Player determineNextPlayer() {
		Player nextPlayer = null;
		for (int k = 0; k < this.players.length; k++) {
			if(this.players[k].equals(this.currentPlayer)) {
				if (k != this.players.length - 1) {
					nextPlayer = this.players[k + 1];
				}
				else {
					nextPlayer = this.players[0];
				}
			}
		}
		return nextPlayer;
	}
	
	public Player determineLastPlayer() {
		return this.players[this.players.length - 1];
	}
	
	public void setLastPlayer(Player lastPlayer) {
		this.lastPlayer = lastPlayer;
	}
	
	public void saveCurrentPlayerInfo() {
		
		for(Player p : this.players) {
			if(p.equals(this.currentPlayer)) {
				p = this.currentPlayer;
				return;
			}
		}
		
	}
	
	/**
	 * This method is used to decide the winner of the math in case of a tie
	 * @param player
	 * @return 
	 */
	private int determineDistanceFromFirstPlayer(Player player) {
		int distance = 0;
		
		for(int k = 0; k < this.players.length; k++) {
			if(players[k].equals(player)) {
				do {
					if(!players[k].equals(this.players[0])) {
						distance++;
					}
					if(k < this.players.length) {
						k++;
					}
					else {
						k = 0;
					}
				}while(distance < this.players.length - 1);
				
				return distance;
			}
			
		}
		return -1;
	}

}
