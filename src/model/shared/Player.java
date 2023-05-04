package model.shared;

import model.commongamearea.PointTile;
import model.personalgamearea.Bookshelf;
import model.personalgamearea.PersonalObjectiveCard;

public class Player {
	private String name;
	private static int number_of_players;
	public final int id;
	public final PersonalObjectiveCard objectiveCard;
	public final Bookshelf bookshelf; 
	public final boolean hasChair;
	private int points;
	private PointTile pointTile1 = null;
	private PointTile pointTile2 = null; 
	private boolean hasEndOfGameToken = false;
	
	public Player(String name, IdGenerator idgenerator) {
		this.name = name;
		this.id = idgenerator.getNewId();
		objectiveCard = new PersonalObjectiveCard(idgenerator.getNewPersonalObjectiveCardId());
		bookshelf = new Bookshelf();
		
		if(this.id == 0)
			this.hasChair = true;
		else
			this.hasChair = false;
	}
	
	public static void setNumberOfPlayers(int count) {
		if(number_of_players == 0)
			number_of_players = count;
	}
	
	public static int getNumberOfPlayers() {
		return number_of_players;
	}
	
	//TODO: make this method less spaghetti or document it better
	public void awardPointTile(PointTile tile) {
		
		//If both spots are empty, then fill point_tile1
		if(pointTile1 == null && pointTile2 == null) {
			pointTile1 = tile;
			this.addPoints(tile.getPoints());
			return;
		}
		
		//If point_tile1 already has a tile from this card, then don't award anything
		if(pointTile1.getCardId() == tile.getCardId())
			return;
		
		//If point_tile2 is empty and point_tile1's tile doesn't have the same id, then fill it point_tile2
		if(pointTile2 == null && pointTile1.getCardId() != tile.getCardId()) {
			pointTile2 = tile;
			this.addPoints(tile.getPoints());
		}
	}
	
	/**
	 * Returns point tile 1 or point tile 2. *Can return null!*
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
}