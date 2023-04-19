package main;

import personalgamearea.Bookshelf;
import personalgamearea.PersonalObjectiveCard;
import personalgamearea.PointTile;
import sharedgamearea.CommonObjectiveCard;

public class Player {
	private String name;
	private static int number_of_players;
	public final int id;
	public final PersonalObjectiveCard objective_card;
	public final Bookshelf bookshelf; 
	private boolean has_game_end_tile = false;
	public final boolean has_chair;
	private int points = 0;
	private PointTile point_tile1 = null; //Point tile received from the respective common objective card 
	private PointTile point_tile2 = null; 
	
	public Player(String name, IdGenerator idgenerator) {
		this.name = name;
		this.id = idgenerator.getNewId();
		objective_card = new PersonalObjectiveCard(idgenerator.getNewPersonalObjectiveCardId());
		bookshelf = new Bookshelf();
		
		if(this.id == 0)
			this.has_chair = true;
		else
			this.has_chair = false;
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
		if(point_tile1 == null && point_tile2 == null) {
			point_tile1 = tile;
			this.addPoints(tile.getPoints());
			return;
		}
		
		//If point_tile1 already has a tile from this card, then don't award anything
		if(point_tile1.getCardId() == tile.getCardId())
			return;
		
		//If point_tile2 is empty and point_tile1's tile doesn't have the same id, then fill it point_tile2
		if(point_tile2 == null && point_tile1.getCardId() != tile.getCardId()) {
			point_tile2 = tile;
			this.addPoints(tile.getPoints());
		}
	}
	
	/**
	 * Returns point tile 1 or point tile 2. *Can return null!*
	 * @param which point tile to return (1 or 2)
	 * @return
	 */
	public PointTile getPointTile(int tile_number) {
		if(tile_number == 1) return this.point_tile1;
		if(tile_number == 2) return this.point_tile2;
		else throw new IllegalArgumentException(tile_number + " is not a valid tile number! Choose tile 1 or 2");
	}
	
	public boolean hasGameEndTile() {
		return this.has_game_end_tile;
	}
	
	public void setGameEndTile(boolean has_game_end_tile) {
		this.has_game_end_tile = has_game_end_tile;
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
}
