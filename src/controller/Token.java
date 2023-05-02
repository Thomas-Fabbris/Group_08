package controller;

import model.shared.Player;

public class Token {
	private int roundNumber;
	private Player currentOwner;
	
	public Token(Player player){
		this.roundNumber = 1;
		this.currentOwner = player;
	}
	
	public void shiftRound(Player players[]) {
		this.roundNumber ++;
		this.moveToNextPlayer(players);
	}
	
	private void moveToNextPlayer(Player players[]) {
		
		for (int k = 0; k < players.length; k++) {
			if(this.currentOwner.equals(players[k])) {
				if(k != players.length - 1) {
					this.currentOwner = players[k++];
				}
				else {
					this.currentOwner = players[0];
				}		
			}
		}
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	public void setCurrentOwner(Player newOwner) {
		this.currentOwner = newOwner;
	}
	
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	public Player getCurrentOwner() {
		return this.currentOwner;
	}
}
