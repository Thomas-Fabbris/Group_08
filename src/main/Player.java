package main;

import java.util.Iterator;

public class Player {
	private String name;
	private static int number_of_players;
	public final int id;
	public static final Player[] players = new Player[4];
	
	public Player(String name, IdGenerator idgenerator) {
		this.name = name;
		this.id = idgenerator.getNewId();
	}
	
	public static void setNumberOfPlayers(int count) {
		if(number_of_players == 0)
			number_of_players = count;
	}
	
	public static int getNumberOfPlayers() {
		return number_of_players;
	}
	
	/**
	 * Get player with specified id
	 * @param id
	 * @return Player
	 */
	public static Player getPlayer(int id) {
		for (int i = 0; i < players.length; i++) {
			if(players[i].id == id) return players[i];
		}
		
		return null;
	}
	
	public boolean equals(Player player) {
		return this.id == player.id;
	}
	
	public String getName() {
		return this.name;
	}	
}
