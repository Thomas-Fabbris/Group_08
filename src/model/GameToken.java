package model;
/**
 * The class {@code GameToken} models an imaginary token that goes from a player to another and identifies the turns
 *
 */
public class GameToken {
	private int roundNumber;
	private Player currentOwner;
	/**
	 * The constructor defines a new instance of the class {@code GameToken}
	 * @param player		first player
	 */
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
	/**
	 * The method {@code moveToNextPlayer} moves the game token to the next player
	 * @param players		the array that contains all the players
	 */
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
