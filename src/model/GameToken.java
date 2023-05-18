package model;

public class GameToken {
	private int roundNumber;
	private Player currentOwner;
	
	public GameToken(Player player){
		if(player == null) {
			throw new NullPointerException("player cannot be set to null while creating a GameToken instance!");
		}
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
				this.setCurrentOwner(players[k++]);
				break;
			}
		}
		
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	public void setCurrentOwner(Player newOwner) {
		if(newOwner == null) {
			throw new NullPointerException("newOwner cannot be set to null when calling GameToken:setCurrentOwner() method!");
		}
		this.currentOwner = newOwner;
	}
	
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	public Player getCurrentOwner() {
		return this.currentOwner;
	}
}
