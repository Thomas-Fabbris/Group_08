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
	 * @param player, first player
	 */
	public GameToken(Player player){
		if(player == null) {
			throw new NullPointerException("player cannot be set to null while creating a GameToken instance!");
		}
		this.roundNumber = 1;
		this.currentOwner = player;
	}
	/**This method is executed by the model of the program when a player pass his turn
	 * @param players, the array that contains all the players
	 */
	public void shiftRound(Player players[]) {
		this.roundNumber ++;
		this.moveToNextPlayer(players);
	}
	/**
	 * The method {@code moveToNextPlayer} moves the game token to the next player
	 * @param players, the array that contains all the players
	 */
	private void moveToNextPlayer(Player players[]) {
	
		for(int k = 0; k < players.length; k++) {
			if(this.currentOwner.equals(players[k])) {
				this.setCurrentOwner(players[k++]);
				break;
			}
		}
		
	}
	/**
	 * The method {@code setRoundNumber} allowes to set the number of rounds completed in the game token
	 * @param roundNumber, an int representing the current no. of rounds
	 */
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	/**
	 * The method {@code setCurrentOwner} allowes to set the new owner for the game token
	 * @param newOwner, the player which will receive the game token
	 */
	public void setCurrentOwner(Player newOwner) {
		if(newOwner == null) {
			throw new NullPointerException("newOwner cannot be set to null when calling GameToken:setCurrentOwner() method!");
		}
		this.currentOwner = newOwner;
	}
	/**
	 * The method {@code getRoundNumber} returns the number of rounds completed during the game
	 * @return round number
	 */
	public int getRoundNumber() {
		return this.roundNumber;
	}
	/**
	 * The method {@code getCurrentOwner} returns the current game token owner
	 * @return the player who ownes the game token
	 */
	public Player getCurrentOwner() {
		return this.currentOwner;
	}
}
