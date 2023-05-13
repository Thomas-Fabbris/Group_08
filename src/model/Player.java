package model;

import model.commongamearea.PointTile;
import model.personalgamearea.Bookshelf;
import model.personalgamearea.PersonalObjectiveCard;
import model.shared.IdGenerator;

public class Player {

	private String name;
	public final int id;
	private int points;
	private boolean hasChair = false;
	private boolean hasEndOfGameToken = false;
	private boolean hasCompletedCommonGoal1 = false;
	private boolean hasCompletedCommonGoal2 = false;

	public final PersonalObjectiveCard objectiveCard;
	public final Bookshelf bookshelf; 
	private PointTile pointTile1;
	private PointTile pointTile2; 

	public Player(String name, IdGenerator idgenerator) {
		this.name = name;
		this.id = idgenerator.getNewId();
		objectiveCard = new PersonalObjectiveCard(idgenerator.getNewPersonalObjectiveCardId());
		bookshelf = new Bookshelf();
	}
	
	/**
	 * Checks if this player can receive this point tile.
	 * If the player is eligible for this point tile, then the player will
	 * receive the points, and the point tile is saved as reference.
	 * @param tile PointTile to award to the player
	 */
	public void awardPointTile(PointTile tile) {
		
		//If both spots are empty, then fill pointTile1
		if(pointTile1 == null && pointTile2 == null) {
			pointTile1 = tile;
			this.addPoints(tile.getPoints());
			return;
		}
		
		//If pointTile1 already has a tile from this card, then don't award anything
		if(pointTile1.getCardId() == tile.getCardId())
			return;
		
		//If pointTile2 is empty and pointTile1 doesn't come from the same card, then fill pointTile2
		if(pointTile2 == null && pointTile1.getCardId() != tile.getCardId()) {
			pointTile2 = tile;
			this.addPoints(tile.getPoints());
		}
	}
	
	/**
	 * Returns point tile 1 or point tile 2.
	 * @param which point tile to return (1 or 2)
	 * @return
	 */
	public PointTile getPointTile(int tileNumber) {
		if(tileNumber == 1) return this.pointTile1;
		if(tileNumber == 2) return this.pointTile2;
		else throw new IllegalArgumentException(tileNumber + " is not a valid tile number! Choose tile 1 or 2");
	}
	
	
	public boolean equals(Player player) {
		return this.id == player.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void setPoints(int value) {
		this.points = value;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public boolean hasEndOfGameToken() {
		return this.hasEndOfGameToken;
	}
	
	public void setEndOfGameToken(boolean hasEndOfGameToken) {
		this.hasEndOfGameToken = hasEndOfGameToken;
	}

	public PersonalObjectiveCard getObjectiveCard() {
		return objectiveCard;
	}

	public boolean hasChair() {
		return hasChair;
	}

	public void setHasChair(boolean hasChair) {
		this.hasChair = hasChair;
	}

	/**
	 * @return the hasEndOfGameToken
	 */
	public boolean isHasEndOfGameToken() {
		return hasEndOfGameToken;
	}

	/**
	 * @param hasEndOfGameToken the hasEndOfGameToken to set
	 */
	public void setHasEndOfGameToken(boolean hasEndOfGameToken) {
		this.hasEndOfGameToken = hasEndOfGameToken;
	}

	/**
	 * @return the pointTile1
	 */
	public PointTile getPointTile1() {
		return pointTile1;
	}

	/**
	 * @param pointTile1 the pointTile1 to set
	 */
	public void setPointTile1(PointTile pointTile1) {
		this.pointTile1 = pointTile1;
	}

	/**
	 * @return the pointTile2
	 */
	public PointTile getPointTile2() {
		return pointTile2;
	}

	/**
	 * @param pointTile2 the pointTile2 to set
	 */
	public void setPointTile2(PointTile pointTile2) {
		this.pointTile2 = pointTile2;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the bookshelf
	 */
	public Bookshelf getBookshelf() {
		return bookshelf;
	}

	/**
	 * @return the hasCompletedCommonGoal1
	 */
	public boolean hasCompletedCommonGoal1() {
		return hasCompletedCommonGoal1;
	}

	/**
	 * @param hasCompletedCommonGoal1 the hasCompletedCommonGoal1 to set
	 */
	public void setHasCompletedCommonGoal1(boolean hasCompletedCommonGoal1) {
		this.hasCompletedCommonGoal1 = hasCompletedCommonGoal1;
	}

	/**
	 * @return the hasCompletedCommonGoal2
	 */
	public boolean hasCompletedCommonGoal2() {
		return hasCompletedCommonGoal2;
	}

	/**
	 * @param hasCompletedCommonGoal2 the hasCompletedCommonGoal2 to set
	 */
	public void setHasCompletedCommonGoal2(boolean hasCompletedCommonGoal2) {
		this.hasCompletedCommonGoal2 = hasCompletedCommonGoal2;
	}
}