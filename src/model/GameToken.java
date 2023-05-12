package model;

public class GameToken {
	private int roundNumber;
	private Player currentOwner;
	
	public GameToken(Player player){
		this.roundNumber = 1;
		this.currentOwner = player;
	}
	
	public void shiftRound(Player players[]) {
		this.roundNumber ++;
		this.moveToNextPlayer(players);
	}
	
	private void moveToNextPlayer(Player players[]) {
		
		for(int k = 0; k < players.length; k++) {
			if(this.currentOwner.equals(players[k])) {
				this.currentOwner = players[k++];
				break;
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
