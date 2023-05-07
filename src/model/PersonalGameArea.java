package model;

import model.shared.Player;

public class PersonalGameArea {

	Player currentPlayer;
	
	public PersonalGameArea() {
		
	}
	
	public void setCurrentPlayer(Player newPlayer) {
		this.currentPlayer = newPlayer;
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
}
