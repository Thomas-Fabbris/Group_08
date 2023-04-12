package main;

public class Player {
	private String name;
	private static int number_of_players;
	public final int id;
	private int points = 0;
	
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
	
	public boolean equals(Player player) {
		return this.id == player.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void givePoints(int points) {
		this.points += points;
	}
	
	public void setPoints(int value) {
		this.points = value;
	}
	
	public int getPoints() {
		return this.points;
	}
}
