package model.commongamearea;

import model.Player;
import model.shared.Tile;
import model.shared.TileType;

public class GameEndTile extends Tile {
	private final Board board;
	public GameEndTile(Board board) {
		super(TileType.GAME_END);
		if(board == null) {
			throw new NullPointerException("board must not be set to null while creating a GameEndTile instance!");
		}
		this.board = board;
		this.enable();
	}
	
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
