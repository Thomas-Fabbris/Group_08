package main;

public class Token {
	private int roundNumber;
	private Player currentOwner;
	
	public Token(Player player){
		this.roundNumber = 1;
		this.currentOwner = player;
	}
	
	public void shiftRound() {
		this.roundNumber ++;
		this.moveToNextPlayer();
		}
	
	private void moveToNextPlayer() {
		
		for (int k = 0; k < Game.players.length; k++) {
			if(this.currentOwner.equals(Game.players[k])) {
				if(k != Game.players.length - 1) {
					this.currentOwner = Game.players[k++];
				}
				else {
					this.currentOwner = Game.players[0];
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
