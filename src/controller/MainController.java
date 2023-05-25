package controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.GameToken;
import model.Player;
import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.commongamearea.CommonGoals;
import model.commongamearea.CommonObjectiveCard;
import model.commongamearea.GameEndTile;
import model.commongamearea.PointTile;
import model.personalgamearea.Bookshelf;
import model.personalgamearea.BookshelfTile;
import model.personalgamearea.PathFind;
import model.personalgamearea.PersonalObjectiveCard;
import model.shared.IdGenerator;
import model.shared.PlayerScoreComparator;
import model.shared.TileType;
import view.CommonGameAreaFrame;
import view.ImageUtils;
import view.PersonalGameAreaFrame;
import view.commongamearea.BoardTileLabel;
import view.personalgamearea.BookshelfLabel;
import view.personalgamearea.BookshelfTileLabel;
import view.personalgamearea.GameEndFrame;

public class MainController {

	private GameState gameState;

	private Player[] players;
	private Player currentPlayer;
	private Player lastPlayer;
	private Player winner;

	private CommonGameArea commonGameArea;
	private CommonGameAreaFrame commonGameAreaFrame;
	private PersonalGameAreaFrame personalGameAreaFrame;

	private GameToken gameToken = null;
	private GameEndTile gameEndTile = null;

	private List<BoardTileController> observers = new ArrayList<>();

	public MainController(PersonalGameAreaFrame personalGameAreaFrame, CommonGameAreaFrame commonGameAreaFrame,
			ArrayList<String> playerNames, CommonGameArea commonGameArea) {
		if (playerNames == null) {
			throw new NullPointerException("players must not be set to null when creating a mainController instance!");
		}
		if (commonGameArea == null) {
			throw new NullPointerException(
					"commonGameArea must not be set to null when creating a MainController instance!");
		}
		if (commonGameAreaFrame == null) {
			throw new NullPointerException(
					"commonGameAreaFrame cannot be set to null while creating a BookShelfTileController instance!");
		}
		if (personalGameAreaFrame == null) {
			throw new NullPointerException(
					"personalGameAreaFrame cannot be set to null while creating a BookShelfTileController instance!");
		}

		setGameState(GameState.RUNNING);
		this.commonGameArea = commonGameArea;
		this.commonGameAreaFrame = commonGameAreaFrame;
		this.personalGameAreaFrame = personalGameAreaFrame;

		IdGenerator idGenerator = new IdGenerator();

		// Initialise players
		this.players = new Player[playerNames.size()];
		createPlayers(playerNames, idGenerator);
		this.lastPlayer = determineLastPlayer();
		this.winner = null;

		// Initialise gameToken
		this.gameToken = new GameToken(this.players[0]);

		// Common game area initialisation
		assignBoardTiles();
		assignCommonObjectiveCards();
		assignPointTiles();
		assignSelectedTilesController();

		// Personal game area initialisation
		assignBookshelfTiles();
		assignNextPlayerButtonController();

		startGame();
	}

	private void startGame() {
		players[0].setHasChair(true);
		
//
//		players[0].bookshelf.setTileType(TileType.BOOKS, 4, 0);
//		players[0].bookshelf.setTileType(TileType.BOOKS, 5, 0);
//		players[0].bookshelf.setTileType(TileType.BOOKS, 2, 0);
//		players[0].bookshelf.setTileType(TileType.BOOKS, 3, 0);
//
//		players[0].bookshelf.setTileType(TileType.BOOKS, 4, 1);
//		players[0].bookshelf.setTileType(TileType.BOOKS, 5, 1);
//		players[0].bookshelf.setTileType(TileType.TROPHIES, 2, 1);
//		players[0].bookshelf.setTileType(TileType.TROPHIES, 3, 1);
//
//		players[0].bookshelf.setTileType(TileType.FRAMES, 4, 2);
//		players[0].bookshelf.setTileType(TileType.FRAMES, 5, 2);
//		players[0].bookshelf.setTileType(TileType.GAMES, 2, 2);
//		players[0].bookshelf.setTileType(TileType.GAMES, 3, 2);
//
//		players[0].bookshelf.setTileType(TileType.FRAMES, 5, 3);
//		players[0].bookshelf.setTileType(TileType.FRAMES, 5, 3);
//		players[0].bookshelf.setTileType(TileType.FRAMES, 2, 3);
//		players[0].bookshelf.setTileType(TileType.FRAMES, 3, 3);
//
		setCurrentPlayer(players[0]);
		updateBookshelfLabel();
	}

	/**
	 * Create players from the names entered in the main menu
	 */
	private void createPlayers(ArrayList<String> names, IdGenerator idGenerator) {
		for (int i = 0; i < names.size(); i++) {
			this.players[i] = new Player(names.get(i), idGenerator);
			System.out.println("[" + this.getClass().getSimpleName() + "] Created player with name '"
					+ players[i].getName() + "' and id " + players[i].id);
		}
	}

	// ----------- Personal game area operations -----------

	/**
	 * Updates the info on the personal game area to reflect the current player's
	 * info
	 * 
	 * @param player
	 */
	public void setCurrentPlayer(Player player) {
		if (player == null) {
			throw new NullPointerException(
					"currentPlayer cannot be set to null when calling MainController:setCurrentPlayer method!");
		}
		this.currentPlayer = player;
		updatePlayerNameText(player);
		updatePointsText(player);
		updateBookshelfLabel(player.bookshelf);
		updatePersonalObjectiveCardLabel(player.getObjectiveCard());
		updatePlayerGameEndTileLabel(player);
		personalGameAreaFrame.getChair().setVisible(player.hasChair());

		for (int i = 0; i < commonGameArea.getCommonObjectiveCards().length; i++) {
			updatePlayerPointTileLabel(player, i);
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void updatePlayerGameEndTileLabel(Player player) {
		JLabel tileLabel = personalGameAreaFrame.getEndOfGameTile();

		if (player.hasEndOfGameToken()) {
			tileLabel.setIcon(personalGameAreaFrame.getGameEndTileIcon());
		} else {
			tileLabel.setIcon(personalGameAreaFrame.getEmptyGameEndIcon());
		}
	}

	/**
	 * updates the point tile's label to reflect the state of the player's point
	 * tile
	 */
	public void updatePlayerPointTileLabel(Player player, int pointTileId) {
		JLabel tileLabel = personalGameAreaFrame.getPointTile(pointTileId);

		if (player.getPointTile(pointTileId) == null) {
			tileLabel.setIcon(personalGameAreaFrame.getEmptyPointTileIcon());
		} else {
			tileLabel.setVisible(true);
			updatePlayerPointTileLabel(player.getPointTile(pointTileId), tileLabel);
		}
	}

	private void assignBookshelfTiles() {
		BookshelfLabel bookshelfLabel = personalGameAreaFrame.getBookshelfLabel();

		for (int row = 0; row < Bookshelf.ROWS; row++) {
			for (int column = 0; column < Bookshelf.COLUMNS; column++) {
				BookshelfTileLabel tileLabel = new BookshelfTileLabel(row, column, bookshelfLabel.getSize());
				tileLabel.addMouseListener(
						new BookshelfTileController(tileLabel, commonGameArea, commonGameAreaFrame, this));
				bookshelfLabel.tiles[row][column] = tileLabel;
				bookshelfLabel.tilesContainer.add(tileLabel);
			}
		}
	}

	/**
	 * Updates the type of each tile displayed on the GUI
	 */
	public void updateBookshelfLabel(Bookshelf bookshelf) {
		BookshelfLabel bookshelfLabel = personalGameAreaFrame.getBookshelfLabel();

		for (int row = 0; row < Bookshelf.ROWS; row++) {
			for (int column = 0; column < Bookshelf.COLUMNS; column++) {
				updateBookshelfTileLabel(bookshelf.getTile(row, column), bookshelfLabel.tiles[row][column]);
			}
		}
	}

	public void updateBookshelfLabel() {
		BookshelfLabel bookshelfLabel = personalGameAreaFrame.getBookshelfLabel();
		Bookshelf bookshelf = currentPlayer.getBookshelf();
		for (int row = 0; row < Bookshelf.ROWS; row++) {
			for (int column = 0; column < Bookshelf.COLUMNS; column++) {
				updateBookshelfTileLabel(bookshelf.getTile(row, column), bookshelfLabel.tiles[row][column]);
			}
		}
	}

	private void updatePersonalObjectiveCardLabel(PersonalObjectiveCard card) {
		JLabel personalObjectiveCard = personalGameAreaFrame.getPersonalObjectiveCardLabel();
		String path = "Assets/Carte_Obiettivo_Personale/Carta_" + card.cardId + ".png";
		ImageIcon icon = ImageUtils.loadImageAsIcon(personalObjectiveCard.getSize(), path);
		personalObjectiveCard.setIcon(icon);
	}

	private void assignNextPlayerButtonController() {
		JLabel button = personalGameAreaFrame.getNextPlayerButton();
		NextPlayerButtonController controller = new NextPlayerButtonController(button, commonGameArea,
				commonGameAreaFrame, this);
		observers.forEach((o) -> controller.addObserver(o));
		observers.clear();
		button.addMouseListener(controller);
	}

	private void updatePlayerNameText(Player player) {
		JLabel playerName = personalGameAreaFrame.getPlayerName();
		playerName.setText(player.getName() + "'s turn");
	}

	public void updatePointsText(Player player) {
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
		updateTileOnScreen(label, () -> "Assets/Point_tiles/" + tile.getPoints() + "p.jpg");
	}

	public GameEndFrame displayGameEndScreen() {
		personalGameAreaFrame.dispose();
		GameEndFrame gameEndScreen = new GameEndFrame();

		for (int i = 0; i < players.length; i++) {
			players[i].awardPointsForTileGroups();
		}
		
		// Sort the array based on the players' score
		Arrays.sort(players, new PlayerScoreComparator());

		List<JLabel> playerNames = gameEndScreen.getPlayerNames();

		// Display on the leaderbord the name of each player in order
		for (int i = 0; i < playerNames.size(); i++) {
			try {
				playerNames.get(i).setText(players[i].getName() + ": [" + players[i].getPoints() + "] points, "
						+ players[i].getPointsFromTileGroups() + " from tile groups");
			} catch (Exception e) {
				playerNames.get(i).setText(null);
			}
		}

		// the array is sorted: players[0] is the winner
		gameEndScreen.getWinnerName().setText(players[0].getName());

		gameEndScreen.setVisible(true);
		return gameEndScreen;
	}

	// ----------- Common game area operations -----------

	/**
	 * Updates the label on each selected tile
	 */
	public void updateSelectedTileLabels() {
		int selectedTilesMaxCount = 3;

		for (int i = 0; i < selectedTilesMaxCount; i++) {
			try {
				updateSelectedTileLabel(i);
			} catch (IndexOutOfBoundsException e) {
				commonGameAreaFrame.getSelectedTile(i).setIcon(null);
			}
		}
	}

	/**
	 * Updates the label on the selected tile with the specified id
	 * 
	 * @param id
	 */
	public void updateSelectedTileLabel(int id) {
		String path = "Assets/tiles/" + currentPlayer.getSelectedTiles().get(id).getType() + ".png";
		Dimension size = commonGameAreaFrame.getSelectedTile(0).getSize();
		commonGameAreaFrame.getSelectedTile(id).setIcon(ImageUtils.loadImageAsIcon(size, path));
	}

	private void assignSelectedTilesController() {
		for (int i = 0; i < commonGameAreaFrame.getSelectedTilesLength(); i++) {
			commonGameAreaFrame.getSelectedTile(i).addMouseListener(new SelectedTileController(i, commonGameAreaFrame, personalGameAreaFrame, this));
		}
	}

	/**
	 * Updates each common objective card's point tile
	 */
	private void assignPointTiles() {
		CommonObjectiveCard[] cards = commonGameArea.getCommonObjectiveCards();
		for (int i = 0; i < cards.length; i++) {
			updateBoardPointTileLabel(cards[i].getLastPointTile(), commonGameAreaFrame.getPointTile(i));
		}
	}

	/**
	 * Updates the amount of points shown on the tile
	 */
	public void updateBoardPointTileLabel(PointTile tile, JLabel label) {
		int rotation = 352;

//		System.out.println(tile.toString());

		if (tile == null) {
			label.setIcon(null);
			return;
		}

		updateTileOnScreen(label, () -> "Assets/Point_tiles/" + tile.getPoints() + "p.jpg", rotation);
	}

	/**
	 * Updates the image of the label to reflect the TileType of the BoardTile
	 */
	public void updateBoardTileLabel(BoardTile tile, BoardTileLabel label) {
		updateTileOnScreen(label, () -> "Assets/tiles/" + tile.getType().toString() + ".png");
	}

	/**
	 * Updates the label of each tile on the board to reflect the tile's type
	 */
	public void updateAllBoardTileLabels() {
		BoardTileLabel[][] boardTileLabels = commonGameAreaFrame.getBoardTilesLabels();
		Board board = commonGameArea.getBoard();

		for (int row = 0; row < Board.BOARD_LENGTH; row++) {
			for (int col = 0; col < Board.BOARD_LENGTH; col++) {
				if (board.isValidPosition(row, col)) {
					updateBoardTileLabel(board.getTile(row, col), boardTileLabels[row][col]);
					boardTileLabels[row][col].setVisible(true);
				}
			}
		}
	}

	/**
	 * Assigns a Label to each Tile on the board and saves it in
	 * commonGameAreaFrame.boardTileLabels. To be used for initialisation only.
	 */
	public void assignBoardTiles() {

		int ROWS = commonGameArea.getBoard().getBoardTiles().length;
		int COLUMNS = commonGameArea.getBoard().getBoardTiles()[0].length;

		Board board = commonGameArea.getBoard();
		JLabel boardLabel = commonGameAreaFrame.getBoardLabel();
		BoardTileLabel[][] boardTileLabels = commonGameAreaFrame.getBoardTilesLabels();

		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				if (board.getValidPositions()[row][column]) {
					BoardTileLabel label = new BoardTileLabel(row, column, boardLabel.getSize());

					BoardTileController listener = new BoardTileController(board.getTile(row, column), label,
							commonGameArea, commonGameAreaFrame, this, personalGameAreaFrame);

					observers.add(listener);
					label.addMouseListener(listener);

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

		CommonObjectiveCard[] cards = commonGameArea.getCommonObjectiveCards();
		JLabel label = null;

		for (int i = 0; i < cards.length; i++) {
			label = commonGameAreaFrame.getCommonObjectiveCard(i);
			label.setIcon(ImageUtils.loadImageAsIcon(label.getSize(),
					"Assets/Carte_Obiettivo_Comune/Carta_" + cards[i].getId() + ".png"));
		}
	}

	/**
	 * Updates the Label's icon; gets the path of the new image from the Supplier
	 */
	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getSize(), pathToNewImage.get());
		tileLabel.setIcon(icon);
	}

	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage, boolean isGrayscale) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getSize(), pathToNewImage.get());

		if (isGrayscale)
			icon = ImageUtils.getGrayImage(icon);

		tileLabel.setIcon(icon);
	}

	private void updateTileOnScreen(JLabel tileLabel, Supplier<String> pathToNewImage, int iconRotation) {
		ImageIcon icon = ImageUtils.loadImageAsIcon(tileLabel.getSize(), pathToNewImage.get());

		if (iconRotation != 0 && iconRotation != 360)
			icon = ImageUtils.rotateIcon(icon, iconRotation);

		tileLabel.setIcon(icon);
	}

	// ----------- General-purpose game operations -----------

	/**
	 * This method checks if the game has finished and if so sets all the necessary
	 * parameters of the game
	 */
	private void checkEndOfGame() {
		if (this.currentPlayer.getBookshelf().isFull()) {
			this.currentPlayer.setEndOfGameToken(true);
			setGameState(GameState.ENDED);
			this.setLastPlayer(determineLastPlayer());
			this.gameEndTile = new GameEndTile(this.commonGameArea.getBoard());
			this.gameEndTile.award(this.currentPlayer);
			saveCurrentPlayerInfo();
		}
	}

	/**
	 * This method allows to skip to the next turn of the game
	 */

	private void nextTurn() {

		if (!this.currentPlayer.getBookshelf().isStateChanged()) {
			System.out.println("It is not possible to skip to the next turn before having completed yours!");
			return;
		} else {
			checkEndOfGame();
			this.currentPlayer.getBookshelf().setStateChanged(false);
			saveCurrentPlayerInfo();
			Player nextPlayer = determineNextPlayer();
			if (!nextPlayer.equals(this.lastPlayer)) {
				this.gameToken.setCurrentOwner(currentPlayer);
				setCurrentPlayer(nextPlayer);
			} else if (getGameState().equals(GameState.ENDED)) {
				computePersonalGoalsPoints();
				computePointsFromTilesGroup();
				this.winner = determineWinner();
			}
		}
	}

	private Player determineWinner() {

		Player[] winners = new Player[this.players.length];
		boolean continua = true;
		int count = 0; // indice interno array winners

		// Determina il/i giocatore/i che hanno totalizzato il maggior punteggio nella
		// partita
		for (Player p : this.players) {
			for (int k = 0; k < count + 1 && continua; k++) {
				if (winners[k] != null && p.getPoints() > winners[k].getPoints()) {
					continua = false;
					winners = new Player[this.players.length];
					count = 0;
					winners[count] = p;
				} else if (winners[k] != null && p.getPoints() == winners[k].getPoints()) {
					count += 1;
					winners[count] = p;
				}
			}
		}

		// In caso di pareggio, sceglie il vincitore sulla base della distanza in senso
		// orario dal primo giocatore
		int maxDistance = 0, currentDistance = 0;
		for (Player w : winners) {
			currentDistance = determineDistanceFromFirstPlayer(w);
			if (currentDistance > maxDistance) {
				maxDistance = currentDistance;
				this.winner = w;
			}
		}

		return this.winner;
	}

	/**
	 * This method computes the points which will be assigned to each player for
	 * fulfilling their personal goals
	 */
	private void computePersonalGoalsPoints() {
		for (Player p : this.players) {
			int satisfiedGoals = p.getObjectiveCard().countSatisfiedGoals(p.getBookshelf());
			p.addPoints(PersonalObjectiveCard.pointsMap.get(satisfiedGoals));
		}
	}

	/**
	 * This method computes the points which will be assigned to each player for
	 * having created group of 3/4/5/6+ adjacent tiles of the same type in his own
	 * bookshelf
	 */
	private void computePointsFromTilesGroup() {
		PathFind pf = null;
		for (Player p : this.players) {
			pf = new PathFind(p.getBookshelf());
			int[] groupList = pf.PointsPathfinding();
			for (int k = 0; k < groupList.length; k++) {
				p.addPoints(CommonGoals.StaticFields.getPointsMap().get(k + 3) * groupList[k]);
			}
		}
	}

	private Player determineNextPlayer() {
		Player nextPlayer = null;
		for (int k = 0; k < this.players.length; k++) {
			if (this.players[k].equals(this.currentPlayer)) {
				if (k != this.players.length - 1) {
					nextPlayer = this.players[k + 1];
				} else {
					nextPlayer = this.players[0];
				}
			}
		}
		return nextPlayer;
	}

	private Player determineLastPlayer() {
		return this.players[this.players.length - 1];
	}

	public void setLastPlayer(Player lastPlayer) {
		this.lastPlayer = lastPlayer;
	}

	private void saveCurrentPlayerInfo() {

		for (Player p : this.players) {
			if (p.equals(this.currentPlayer)) {
				p = this.currentPlayer;
				return;
			}
		}

	}

	/**
	 * This method is used to decide the winner of the math in case of a tie
	 * 
	 * @param player
	 * @return
	 */
	private int determineDistanceFromFirstPlayer(Player player) {
		int distance = 0;

		for (int k = 0; k < this.players.length; k++) {
			if (players[k].equals(player)) {
				do {

					if (!players[k].equals(this.players[0])) {
						distance++;
					} else {
						distance = 0;
						return distance;
					}

					if (k < this.players.length) {
						k++;
					} else {
						k = 0;
					}

				} while (distance < this.players.length - 1);

				return distance;
			}

		}
		return -1;
	}

	/**
	 * Returns the player with the specified id (if such player exists)
	 * 
	 * @param playerId
	 * @return Player with id PlayerId
	 */
	public Player getPlayer(int playerId) {
		if (playerId < 0 || playerId > players.length)
			throw new ArrayIndexOutOfBoundsException("Player with id " + playerId + " doesn't exist!");
		return players[playerId];
	}

	public Player getLastPlayer() {
		return lastPlayer;
	}

	/**
	 * @return the gameToken
	 */
	public GameToken getGameToken() {
		return gameToken;
	}

	/**
	 * @return the personalGameAreaFrame
	 */
	public PersonalGameAreaFrame getPersonalGameAreaFrame() {
		return personalGameAreaFrame;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
