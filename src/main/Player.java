package main;

import gui.PointTileLabel;
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
	
//	public void awardPointTile(CommonObjectiveCard card, int points, PointTileLabel label) {
//		this.addPoints(points);
//
//		//Do not award anything if player has already received points form both cards
//		//-1 is the default id in PointTileInfo
//		if(point_tile1.getCard_id() != -1 && point_tile2.getCard_id() != -1)
//			return;
//		
//		//Do not award anything if the player has already received points form this card
//		if(point_tile1.getCard_id() == card.getId() || point_tile2.getCard_id() == card.getId())
//			return;
//		
//	}
	
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
