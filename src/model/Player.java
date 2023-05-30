package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.commongamearea.BoardTile;
import model.commongamearea.GameEndTile;
import model.commongamearea.PointTile;
import model.personalgamearea.Bookshelf;
import model.personalgamearea.PathFind;
import model.personalgamearea.PersonalObjectiveCard;
import model.shared.IdGenerator;

/**
 * 	The class {@code Player} creates an object Player that is a user of the game.
 *  
 */
public class Player {

	private String name;
	public final int id;
	private int points = 0;
	private int pointsFromTileGroups = 0;
	private boolean hasChair = false;
	private boolean hasEndOfGameToken = false;
	private boolean[] completedCommonGoals;

	private int previousObjectiveCardMatches = 0;
	private int previousObjetiveCardPoints = 0;

	private int selectedColumn = -1; // Identifies which column on the bookshelf the player is adding the tiles
										// to (value -1 means no column has been decided yet)

	private List<BoardTile> selectedTiles; // List of tiles selected from the board (can have max 3 tiles at a time)
	public final PersonalObjectiveCard objectiveCard;
	public final Bookshelf bookshelf;
	private PointTile[] pointTiles;

	/**
	 * This is the constructor of the class Player. 
	 * 
	 * @param name        (name of the player)
	 * @param idgenerator (instance of the class) {@link IdGenerator}
	 */

	public Player(String name, IdGenerator idGenerator) {
		if (name == null) {
			throw new NullPointerException("name cannot be set to null when creating a Player instance!");
		}
		if (idGenerator == null) {
			throw new NullPointerException("players cannot be set to null when calling GameToken:shiftRound method!");
		}
		this.name = name;
		this.id = idGenerator.getNewId();
		this.completedCommonGoals = new boolean[] { false, false };
		this.pointTiles = new PointTile[2];
		this.selectedTiles = new LinkedList<BoardTile>();

		objectiveCard = new PersonalObjectiveCard(idGenerator);
		
		bookshelf = new Bookshelf(this);
	}

	/**
	 * This method checks if the model.Player can receive the PointTile. 
	 * If the player can effectively receive the PointTile, he receives the 
	 * corresponding amount of points.
	 * 
	 * The player can receive this point tile if the slot with the same Roman number
	 * as the card is empty. 
	 * 
	 * @param tile {@link PointTile PointTile} (to be assigned to the player)
	 * 
	 */
	public void awardPointTile(PointTile tile) {
		if (tile == null) {
			throw new NullPointerException("tile cannot be set to null while calling Player:awardPointTile() method!");
		}

		if (pointTiles[tile.getRomanNumber()] == null) {
			pointTiles[tile.getRomanNumber()] = tile;
			setCompletedCommonGoal(tile.getRomanNumber(), true);
			addPoints(tile.getPoints());
		}
	}

	/**
	 * This method allows to reward the player with the points get from each group of adjacent tiles in
	 * the player's bookshelf.  
	 * This method should be run only once for each player before the end of the game. 
	 * 
	 * @param player
	 * @return pointsTotal (total number of points the player received from this method)
	 *         
	 */
	public int awardPointsForTileGroups() {
		// Each element of this array is a group of adjacent tiles in the bookshelf, in
		// particular the integer value specifies the number of tiles in said group.
		// {3, 4, 4, 3} means 4 groups, two with 3 tiles and two with 4 tiles.
		PathFind pf = new PathFind(bookshelf);
		int[] tileGroups = pf.PointsPathfinding();

		int points = 0;
		int pointsTotal = 0;

		System.out.println("[" +this.getClass().getSimpleName()+ "] "+ name+ "'s points from tile groups: " +Arrays.toString(tileGroups));

		for (int i = 0; i < tileGroups.length; i++) {
			points = pf.mapTilesGroupSizeToPoints(tileGroups[i]);
			addPoints(points);
			pointsTotal += points;
		}

		this.pointsFromTileGroups = pointsTotal;
		return pointsTotal;
	}

	/**
	 * This method {@code getPointTile} returns the {@link PointTile PointTile}
	 * 
	 * @param tileNumber (number of the {@code PointTile PointTile} to be returned it can be 0 or 1)
	 * 
	 * @return pointTile (the {@link PointTile PointTile} requested) 
	 */
	public PointTile getPointTile(int tileNumber) {
		return pointTiles[tileNumber];
	}

	/**
	 * This method {@code equals} allows to compare two players by checking their id {@code id}. 
	 * The id is assigned to a player at the beginning of the game. 
	 * 
	 * @param player ( the {@link Player player} to be compared with the instance of the object
	 * 					that calls this method)
	 * 
	 * @return {@code true} (if the two players are the same)
	 *         {@code false} (in all the other cases possibles)
	 */
	public boolean equals(Player player) {
		return this.id == player.id;
	}

	/**
	 * This method is a getter. 
	 * 
	 * @return name (of the {@link model.Player player} that called this method)
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method assigns to the player new points.
	 * 
	 * @param points (to be assigned to {@link model.Player player})
	 */
	public void addPoints(int points) {
		this.points += points;
	}

	/**
	 * This method is a getter
	 *  
	 * @return points  (current points of the {@link model.Player player})
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * This method verifies if the {@link model.Player player} who called the method
	 * caused the end of the game. 
	 * 
	 * @return {@code true} if the {@link model.Player player} owns the {@link GameEndTile EndOfGameToken}
	 */
	public boolean hasEndOfGameToken() {
		return this.hasEndOfGameToken;
	}
	
	/**
	 * This method assigns the EndOfGameToken
	 * 
	 * @param hasEndOfGameToken
	 */
	public void setEndOfGameToken(boolean hasEndOfGameToken) {
		this.hasEndOfGameToken = hasEndOfGameToken;
	}

	/**
	 * This method {@code getObjectiveCard} returns the {@link PersonalObjectiveCard} owned by the 
	 * {@link model.Player player} during the current game. 
	 * 
	 * @return {@link PersonalObjectiveCard} of {@link model.Player player}
	 */
	public PersonalObjectiveCard getObjectiveCard() {
		return objectiveCard;
	}

	/**
	 * The method {@code hasEndOfGameToken} checks if  the {@link model.Player giocatore} 
	 * who called the method itself, is the one who started the game. 
	 * 
	 * @return {@code true} if the {@link model.Player player} began the current game
	 */
	public boolean hasChair() {
		return hasChair;
	}

	/**
	 * This method assigns the chair to a player.
	 * 
	 * @param hasChair
	 */
	public void setHasChair(boolean hasChair) {
		this.hasChair = hasChair;
	}
	
	/**
	 * This method is a getter. 
	 * 
	 * @return id (of the current player)
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This method is a getter. 
	 * 
	 * @return bookshelf (of the current player)
	 */
	public Bookshelf getBookshelf() {
		return bookshelf;
	}

	/**
	 * This method returns whether the player has completed the specified common goal (0 or 1)
	 * 
	 * @param id - 0 or 1
	 * @return completedCommonGoals[id]
	 */
	public boolean hasCompletedCommonGoal(int id) {
		return completedCommonGoals[id];
	}

	/**
	 * This method sets the flag to true or false for the specified common goal (0 or 1)
	 * 
	 * @param id - 0 or 1
	 * @return completedCommonGoals[id]
	 */
	public void setCompletedCommonGoal(int id, boolean completed) {
		completedCommonGoals[id] = completed;
	}
	
	/**
	 * This method is a getter. 
	 * 
	 * @return selectedTiles
	 */
	public List<BoardTile> getSelectedTiles() {
		return selectedTiles;
	}
	
	/**
	 * This method is a getter. 
	 * @param id
	 * @return selectedTiles (from the specified id)
	 */
	public BoardTile getSelectedTile(int id) {
		return selectedTiles.get(id);
	}

	/**
	 * This method returns the column selected by the player while inserting the tiles in the
	 * bookshelf. Value -1 means that the player has not yet selected a column.
	 * 
	 * @return selectedColumn
	 */
	public int getSelectedColumn() {
		return selectedColumn;
	}

	/**
	 * This method sets the column selected by the player when inserting a tile in the bookshelf.
	 * 
	 * @param bookshelfColumn which column the player is inserting the tiles into.
	 *                        Must be 0, 1, 2, 3 or 4.
	 */
	public void setSelectedColumn(int bookshelfColumn) {
		if (bookshelfColumn < 0 || bookshelfColumn > 4) {
			throw new IllegalArgumentException(
					"The player must select a column between 0 and 4 (inclusive): " + bookshelfColumn);
		}
		this.selectedColumn = bookshelfColumn;
	}

	/**
	 * This method resets the flag to -1, which means that the player has not yet selected a row. 
	 */
	public void resetSelectedColumn() {
		this.selectedColumn = -1;
	}

	/**
	 * This method returns true if the player has already added a tile to the bookshelf (which
	 * means the player has selected a column to insert the tiles into). 
	 * 
	 * @return boolean
	 */
	public boolean hasSelectedColumn() {
		return this.selectedColumn != -1;
	}

	/**
	 * This method is a getter. 
	 * 
	 * @return previousObjectiveCardMatches
	 */
	public int getPreviousObjectiveCardMatches() {
		return previousObjectiveCardMatches;
	}
	
	/**
	 * This method is a setter. 
	 * 
	 * @param matchesOnPersonalObjectiveCard
	 */
	public void setPreviousObjectiveCardMatches(int matchesOnPersonalObjectiveCard) {
		this.previousObjectiveCardMatches = matchesOnPersonalObjectiveCard;
	}
	
	/**
	 * This method is a getter. 
	 * 
	 * @return getPreviousObjetiveCardPoints
	 */
	public int getPreviousObjetiveCardPoints() {
		return previousObjetiveCardPoints;
	}
	
	/**
	 * This method is a setter. 
	 * 
	 * @param previousObjetiveCardPoints
	 */
	public void setPreviousObjetiveCardPoints(int previousObjetiveCardPoints) {
		this.previousObjetiveCardPoints = previousObjetiveCardPoints;
	}
	
	/**
	 * This method is a getter. 
	 * 
	 * @return pointsFromTileGroups
	 */
	public int getPointsFromTileGroups() {
		return pointsFromTileGroups;
	}

}