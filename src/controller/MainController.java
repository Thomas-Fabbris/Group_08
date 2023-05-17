package controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CommonGameArea;
import model.GameToken;
import model.Player;
import model.commongamearea.Board;
import model.commongamearea.BoardTile;
import model.commongamearea.CommonObjectiveCard;
import model.commongamearea.GameEndTile;
import model.commongamearea.PointTile;
import model.personalgamearea.Bookshelf;
import model.personalgamearea.BookshelfTile;
import model.personalgamearea.CommonGoals;
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
	private Player winner;

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
		this.lastPlayer = determineLastPlayer();
		this.winner = null;

		// Initialise gameToken
		this.gameToken = new GameToken(this.currentPlayer);
		
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
//		players[0].bookshelf.fillRandom();
		setCurrentPlayer(players[0]);
		currentPlayer.bookshelf.setTileType(TileType.BOOKS, 2, 2);
		currentPlayer.bookshelf.setTileType(TileType.CATS, 3, 2);
		
		updateBookshelfLabel();
		
		System.out.println("Matches: " + players[0].getObjectiveCard().countSatisfiedGoals(players[0].bookshelf));
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
	public void setCurrentPlayer(Player player) {
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

	public Player getCurrentPlayer() {
		return currentPlayer;
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
				BookshelfTileLabel tileLabel = new BookshelfTileLabel(row, column, bookshelfLabel.getSize());
				tileLabel.addMouseListener(new BookshelfTileController(tileLabel, commonGameAreaFrame, personalGameAreaFrame, this));
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
		String path = "Assets/Carte_Obiettivo_Personale/Carta_X.png".replace("X", Integer.toString(card.cardId));
		ImageIcon icon = ImageUtils.loadImageAsIcon(personalObjectiveCard.getSize(), path);
		personalObjectiveCard.setIcon(icon);
	}

	private void assignNextPlayerButtonController() {
		JLabel button = personalGameAreaFrame.getNextPlayerButton();
		button.addMouseListener(new NextPlayerButtonController(button, commonGameArea, commonGameAreaFrame, this));
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
	 * @param id
	 */
	public void updateSelectedTileLabel(int id) {
		String path = "Assets/tiles/" +currentPlayer.getSelectedTiles().get(id).getType()+ ".png";
		Dimension size = commonGameAreaFrame.getSelectedTile1().getSize();
		commonGameAreaFrame.getSelectedTile(id).setIcon(ImageUtils.loadImageAsIcon(size, path));
	}
	
	private void assignSelectedTilesController() {
		commonGameAreaFrame.getSelectedTile1()
				.addMouseListener(new SelectedTileController(0, commonGameAreaFrame, personalGameAreaFrame, this));
		commonGameAreaFrame.getSelectedTile2()
				.addMouseListener(new SelectedTileController(1, commonGameAreaFrame, personalGameAreaFrame, this));
		commonGameAreaFrame.getSelectedTile3()
				.addMouseListener(new SelectedTileController(2, commonGameAreaFrame, personalGameAreaFrame, this));
	}

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
	public void updateBoardTileLabel(BoardTile tile, BoardTileLabel label) {
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

		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				if (board.getValidPositions()[row][column]) {
					BoardTileLabel label = new BoardTileLabel(row, column, boardLabel.getSize());

					label.addMouseListener(new BoardTileController(board, board.getTile(row, column), label,
							commonGameArea, commonGameAreaFrame, this));

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
	 * This method checks if the game has finished and if so sets all the necessary
	 * parameters of the game
	 */
	private void checkEndOfGame() {
		if (this.currentPlayer.getBookshelf().isFull()) {
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

	private void nextTurn() {

		if (!this.currentPlayer.getBookshelf().isStateChanged()) {
			System.out.println("It is not possible to skip to the next turn before having completed yours!");
			return;
		} else {
			checkCommonGoals();
			checkEndOfGame();
			this.currentPlayer.getBookshelf().setStateChanged(false);
			saveCurrentPlayerInfo();
			Player nextPlayer = determineNextPlayer();
			if (!nextPlayer.equals(this.lastPlayer)) {

				if (this.commonGameArea.getBoard().refillCheck()) {
					this.commonGameArea.getBoard().refill();
					assignBoardTiles();
				}

				this.gameToken.setCurrentOwner(currentPlayer);
				setCurrentPlayer(nextPlayer);
			} else if (this.gameState.equals(GameState.ENDED)) {
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
		for (Player p : this.players) {
			int groupOf3Number = CommonGoals.CountGroupOf3TilesAdjacent(p.getBookshelf());
			int groupOf4Number = CommonGoals.CountGroupOf4TilesAdjacent(p.getBookshelf());
			int groupOf5Number = CommonGoals.CountGroupOf5TilesAdjacent(p.getBookshelf());
			int groupOf6Number = CommonGoals.CountGroupOf6OrMoreTilesAdjacent(p.getBookshelf());

			p.addPoints(CommonGoals.StaticFields.getPointsMap().get(3) * groupOf3Number);
			p.addPoints(CommonGoals.StaticFields.getPointsMap().get(4) * groupOf4Number);
			p.addPoints(CommonGoals.StaticFields.getPointsMap().get(5) * groupOf5Number);
			p.addPoints(CommonGoals.StaticFields.getPointsMap().get(6) * groupOf6Number);

		}
	}

	private void checkCommonGoals() {
		if (!this.currentPlayer.hasCompletedCommonGoal1() && this.commonGameArea.getCard1().getRelatedCommonGoal()
				.checkCommonGoal(this.currentPlayer.getBookshelf())) {
			this.currentPlayer.setHasCompletedCommonGoal1(true);
			this.commonGameArea.getCard1().award(this.currentPlayer);
		}
		if (!this.currentPlayer.hasCompletedCommonGoal2() && this.commonGameArea.getCard2().getRelatedCommonGoal()
				.checkCommonGoal(this.currentPlayer.getBookshelf())) {
			this.currentPlayer.setHasCompletedCommonGoal2(true);
			this.commonGameArea.getCard2().award(this.currentPlayer);
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

}
