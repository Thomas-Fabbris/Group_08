package main;

import personalgamearea.PersonalObjectiveCard;

public class Player {
	private String name;
	private static int number_of_players;
	public final int id;
	public final PersonalObjectiveCard objective_card;
//	public final Shelf shelf;
	private boolean has_game_end_tile = false;
	public final boolean has_chair;
	private int points = 0;
	
	public Player(String name, IdGenerator idgenerator) {
		this.name = name;
		this.id = idgenerator.getNewId();
		objective_card = new PersonalObjectiveCard(idgenerator.getNewPersonalObjectiveCardId());
		
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
