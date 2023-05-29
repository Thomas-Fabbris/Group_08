package model.commongamearea;

import model.Player;
import model.shared.Tile;
import model.shared.TileType;
/**
 * The class {@code GameEndTile} models a particular tile that indicates the last playable turn
 * 
 *
 */
public class GameEndTile extends Tile {
	private final Board board;
	
	/**
	 * The constructor defines a new instance of the class {@code GameEndTile}
	 * @param board		the game board
	 */
	public GameEndTile(Board board) {
		super(TileType.GAME_END);
		if(board == null) {
			throw new NullPointerException("board must not be set to null while creating a GameEndTile instance!");
		}
		this.board = board;
		this.enable();
	}
	/**
	 * The method {@code award} gives points to the first player who fills the bookshelf
	 * @param player 		first player that fills the bookshelf
	 */
	public void award(Player player) {
		player.addPoints(1);
		player.setEndOfGameToken(true);
		this.disable();
	}
	
	public boolean hasBeenAwarded() {
		return !this.isActive;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
}
